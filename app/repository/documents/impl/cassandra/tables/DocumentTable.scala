package repository.documents.impl.cassandra.tables

import java.time.LocalDateTime

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import com.outworkers.phantom.jdk8._
import domain.documents.Document

import scala.concurrent.Future

abstract class DocumentTable extends Table[DocumentTable, Document]{

  object email extends StringColumn with PartitionKey

  object documentsId extends StringColumn

  object documentTypeId extends StringColumn

  object description extends StringColumn

  object url extends StringColumn

  object mime extends StringColumn

  object date extends Col[LocalDateTime]

  object permission extends StringColumn
}

abstract class DocumentTableImpl extends DocumentTable with RootConnector{

  override  lazy val tableName ="Document"


  def saveEntity(entity:Document): Future[ResultSet] ={
    insert
      .value(_.documentsId, entity.documentId)
      .value(_.documentTypeId, entity.documentTypeId)
      .value(_.description, entity.description)
      .value(_.url, entity.url)
      .value(_.mime, entity.mime)
      .value(_.date, entity.date)
      .value(_.permission, entity.permission)
      .future()

  }

  def getEntity(email: String): Future[Option[Document]] = {
    select
      .where(_.email eqs email)
      .one()
  }

  def getEntities: Future[Seq[Document]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(email: String): Future[ResultSet] = {
    delete
      .where(_.email eqs email)
      .future()
  }


}
