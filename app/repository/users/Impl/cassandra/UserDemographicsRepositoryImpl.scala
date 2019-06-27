package repository.users.Impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.users.UserDemographics
import repository.users.Impl.cassandra.tables.UserDemographicsTableImpl
import repository.users.UserDemographicsRepository
//import util.connections.DataConnection

import util.connections.{DataConnection, PgDBConnection}

import scala.concurrent.Future

class UserDemographicsRepositoryImpl extends UserDemographicsRepository{
  override def saveEntity(entity: UserDemographics): Future[Boolean] = {
    UserDemographicsDatabase.userDemographicsTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[UserDemographics]] = {
    UserDemographicsDatabase.userDemographicsTable.getEntities
  }

  override def getEntity(userDemographicsId: String): Future[Option[UserDemographics]] = {
    UserDemographicsDatabase.userDemographicsTable.getEntity(userDemographicsId)
  }

  override def deleteEntity(entity: UserDemographics): Future[Boolean] = {
    UserDemographicsDatabase.userDemographicsTable.deleteEntity(entity.userDemographicsId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    UserDemographicsDatabase.userDemographicsTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}
class UserDemographicsDatabase(override val connector: KeySpaceDef) extends Database[UserDemographicsDatabase](connector) {
  object userDemographicsTable extends UserDemographicsTableImpl with connector.Connector

}

object UserDemographicsDatabase extends UserDemographicsDatabase(DataConnection.connector)