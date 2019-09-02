package repository.security.impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.security.ResetToken

import scala.concurrent.Future


abstract class ResetTokenTable extends Table[ResetTokenTable, ResetToken] {

  object resetokenvalue extends StringColumn with PartitionKey

  object email extends StringColumn

}

abstract class ResetTokenTableImpl extends ResetTokenTable with RootConnector {

  override lazy val tableName = "resettokens"

  def saveEntity(entity: ResetToken): Future[ResultSet] = {
    insert
      .value(_.resetokenvalue, entity.resetokenvalue)
      .value(_.email, entity.email)
      .ttl(86400)
      .future()
  }

  def getEntity(id: String): Future[Option[ResetToken]] = {
    select
      .where(_.resetokenvalue eqs id)
      .one()
  }

  def getEntities: Future[Seq[ResetToken]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(id: String): Future[ResultSet] = {
    delete
      .where(_.resetokenvalue eqs id)
      .future()
  }
}