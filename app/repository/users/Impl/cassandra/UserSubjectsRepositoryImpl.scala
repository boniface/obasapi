package repository.users.Impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.users.UserSubjects
import repository.users.Impl.cassandra.tables.UserSubjectsTableImpl
import repository.users.UserSubjectsRepository
//import util.connections.DataConnection

import util.connections.{DataConnection, PgDBConnection}

import scala.concurrent.Future

class UserSubjectsRepositoryImpl extends UserSubjectsRepository{
  override def saveEntity(entity: UserSubjects): Future[Boolean] = {
    UserSubjectsDatabase.userSubjectsTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[UserSubjects]] = {
    UserSubjectsDatabase.userSubjectsTable.getEntities
  }

  override def getEntity(userSubjectId: String): Future[Option[UserSubjects]] = {
    UserSubjectsDatabase.userSubjectsTable.getEntity(userSubjectId)
  }

  override def deleteEntity(entity: UserSubjects): Future[Boolean] = {
    UserSubjectsDatabase.userSubjectsTable.deleteEntity(entity.userSubjectId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    UserSubjectsDatabase.userSubjectsTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}
class UserSubjectsDatabase(override val connector: KeySpaceDef) extends Database[UserSubjectsDatabase](connector) {
  object userSubjectsTable extends UserSubjectsTableImpl with connector.Connector

}

object UserSubjectsDatabase extends UserSubjectsDatabase(DataConnection.connector)