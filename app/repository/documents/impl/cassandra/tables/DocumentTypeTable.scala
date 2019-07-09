package repository.documents.impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.documents.DocumentType

import scala.concurrent.Future

abstract class DocumentTypeTable extends  Table[DocumentTypeTable, DocumentType]{

  object documentTypeId extends StringColumn with PartitionKey

  object documentTypename extends StringColumn

}

abstract class DocumentTypeTableImpl extends DocumentTypeTable with RootConnector{

  override  lazy val tableName ="DocumentType"


  def saveEntity(entity:DocumentType): Future[ResultSet] ={
    insert
      .value(_.documentTypeId, entity.documentTypeId)
      .value(_.documentTypename, entity.documentTypename)
      .future()

  }

  def getEntity(documentTypeId: String): Future[Option[DocumentType]] = {
    select
      .where(_.documentTypeId eqs documentTypeId)
      .one()
  }

  def getEntities: Future[Seq[DocumentType]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(documentTypeId: String): Future[ResultSet] = {
    delete
      .where(_.documentTypeId eqs documentTypeId)
      .future()
  }


}
