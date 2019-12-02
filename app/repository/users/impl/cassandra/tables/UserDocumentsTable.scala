package repository.users.impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.users.UserDocument

import scala.concurrent.Future

abstract class UserDocumentsTable extends Table[UserDocumentsTable, UserDocument] {

  object userDocumentsId extends StringColumn with PartitionKey

  object documentId extends StringColumn

}

abstract class UserDocumentsTableImpl extends UserDocumentsTable with RootConnector {

  override lazy val tableName = "userDocuments"

  def saveEntity(entity: UserDocument): Future[ResultSet] = {
    insert
      .value(_.userDocumentsId, entity.userId)
      .value(_.documentId, entity.documentId)
      .future()
  }

  def getEntity(userDocumentsId: String): Future[Option[UserDocument]] = {
    select
      .where(_.userDocumentsId eqs userDocumentsId)
      .one()
  }

  def getEntities: Future[Seq[UserDocument]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(userDocumentsId: String): Future[ResultSet] = {
    delete
      .where(_.userDocumentsId eqs userDocumentsId)
      .future()
  }
}