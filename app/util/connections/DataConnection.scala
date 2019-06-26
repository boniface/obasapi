package util.connections

import java.net.InetAddress

import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy
import com.datastax.driver.core.{PoolingOptions, SocketOptions}
import com.outworkers.phantom.builder.serializers.KeySpaceSerializer
import com.outworkers.phantom.connectors.ContactPoints
import com.outworkers.phantom.dsl._
import com.typesafe.config.{Config, ConfigFactory}

import scala.collection.JavaConverters._


object DataConnection {

  val config: Config = ConfigFactory.load()

  def PORT = 9042

  def connectionTimeoutMillis = 70000000

  // Default is 5000
  def readTimeoutMillis = 150000000

  // Default is 12000
  val hosts: Seq[String] = config.getStringList("cassandra.host").asScala.toList
  val dataCenter1: String = config.getString("cassandra.dataCenter1")
  val dataCenter2: String = config.getString("cassandra.dataCenter2")
  val inets: Seq[InetAddress] = hosts.map(InetAddress.getByName)
  val keySpace: String = config.getString("cassandra.keySpace")
  val clusterName: String = config.getString("cassandra.clusterName")

  lazy val connector: CassandraConnection = testingConnector
  val keySpaceQuery: KeySpaceSerializer = testingKeySpaceQuery

  //======== For Local Development ============
  def testingKeySpaceQuery: KeySpaceSerializer = KeySpace(keySpace).ifNotExists()
    .`with`(replication eqs SimpleStrategy.replication_factor(1))
    .and(durable_writes eqs true)

  def testingConnector: CassandraConnection = ContactPoints(hosts, PORT)
    .withClusterBuilder(_.withSocketOptions(
      new SocketOptions()
        .setConnectTimeoutMillis(20000)
        .setReadTimeoutMillis(20000))
    ).noHeartbeat().keySpace(
    KeySpace(keySpace).ifNotExists().`with`(
      replication eqs SimpleStrategy.replication_factor(1))
  )

  // == For Production Deployment

  def productionKeySpaceQuery: KeySpaceSerializer = KeySpace(keySpace).ifNotExists()
    .`with`(replication eqs NetworkTopologyStrategy
      .data_center(dataCenter1, 3)
      .data_center(dataCenter2, 2)
    ).and(durable_writes eqs true)


  def productionConnector: CassandraConnection = ContactPoints(hosts, PORT)
    .withClusterBuilder(
      _.withClusterName(clusterName)
        .withSocketOptions(new SocketOptions()
          .setReadTimeoutMillis(readTimeoutMillis)
          .setConnectTimeoutMillis(connectionTimeoutMillis))
        .withPoolingOptions(new PoolingOptions()
          .setMaxQueueSize(100000)
          .setPoolTimeoutMillis(20000))
        .withLoadBalancingPolicy(
          new DCAwareRoundRobinPolicy.Builder()
            .withUsedHostsPerRemoteDc(1)
            .withLocalDc(dataCenter2).build()
        )
    ).noHeartbeat().keySpace(keySpaceQuery)


}








