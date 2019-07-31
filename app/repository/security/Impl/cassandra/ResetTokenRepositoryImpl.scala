package repository.security.Impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.security.ResetToken
import repository.security.Impl.cassandra.tables.ResetTokenTableImpl
import repository.security.ResetTokenRepository
import util.connections.DataConnection

import scala.concurrent.Future

class ResetTokenRepositoryImpl extends ResetTokenRepository {

  override def saveEntity(entity: ResetToken): Future[Boolean] = {
    ResetTokenDatabase.resetTokenTable.saveEntity(entity) map ( result => result.isExhausted())
  }

  override def getEntities: Future[Seq[ResetToken]] = {
    ResetTokenDatabase.resetTokenTable.getEntities
  }

  override def getEntity(id: String): Future[Option[ResetToken]] = {
    ResetTokenDatabase.resetTokenTable.getEntity(id)
  }

  override def deleteEntity(entity: ResetToken): Future[Boolean] = {
    ResetTokenDatabase.resetTokenTable.deleteEntity(entity.resetokenvalue).map(result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace

    implicit def session: Session = DataConnection.connector.session

    ResetTokenDatabase.resetTokenTable.create.ifNotExists().future().map(result => result.head.isExhausted())
  }
}

class ResetTokenDatabase(override val connector: KeySpaceDef) extends Database[ResetTokenDatabase](connector) {

  object resetTokenTable extends ResetTokenTableImpl  with connector.Connector

}

object ResetTokenDatabase extends ResetTokenDatabase(DataConnection.connector)


