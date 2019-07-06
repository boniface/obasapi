package repository.users.impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.users.UserInstitution
import repository.users.impl.cassandra.tables.UserInstitutionTableImpl
import repository.users.UserInstitutionRepository
//import util.connections.DataConnection

import util.connections.{DataConnection, PgDBConnection}

import scala.concurrent.Future

class UserInstitutionRepositoryImpl extends UserInstitutionRepository{
  override def saveEntity(entity: UserInstitution): Future[Boolean] = {
    UserInstitutionDatabase.userInstitutionTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[UserInstitution]] = {
    UserInstitutionDatabase.userInstitutionTable.getEntities
  }

  override def getEntity(userInstitutionId: String): Future[Option[UserInstitution]] = {
    UserInstitutionDatabase.userInstitutionTable.getEntity(userInstitutionId)
  }

  override def deleteEntity(entity: UserInstitution): Future[Boolean] = {
    UserInstitutionDatabase.userInstitutionTable.deleteEntity(entity.userInstitutionId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    UserInstitutionDatabase.userInstitutionTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}
class UserInstitutionDatabase(override val connector: KeySpaceDef) extends Database[UserInstitutionDatabase](connector) {
  object userInstitutionTable extends UserInstitutionTableImpl with connector.Connector

}

object UserInstitutionDatabase extends UserInstitutionDatabase(DataConnection.connector)