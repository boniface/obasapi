package repository.users.impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.users.UserDocuments

import scala.concurrent.Future

abstract class UserDocumentsTable extends Table[UserDocumentsTable, UserDocuments] {

  object userDocumentsId extends StringColumn with PartitionKey

  object documentId extends StringColumn

}

abstract class UserDocumentsTableImpl extends UserDocumentsTable with RootConnector {

  override lazy val tableName = "userDocuments"

  def saveEntity(entity: UserDocuments): Future[ResultSet] = {
    insert
      .value(_.userDocumentsId, entity.userDocumentsId)
      .value(_.documentId, entity.documentId)
      .future()
  }

  def getEntity(userDocumentsId: String): Future[Option[UserDocuments]] = {
    select
      .where(_.userDocumentsId eqs userDocumentsId)
      .one()
  }

  def getEntities: Future[Seq[UserDocuments]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(userDocumentsId: String): Future[ResultSet] = {
    delete
      .where(_.userDocumentsId eqs userDocumentsId)
      .future()
  }
}