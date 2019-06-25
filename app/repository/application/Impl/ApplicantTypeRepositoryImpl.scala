package repository.application.Impl

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.application.ApplicantType
import repository.application.ApplicantTypeRepository
import repository.application.Impl.cassandra.tables.ApplicantTypeTableImpl
import repository.mail.MailApiRepository
import util.connections.DataConnection

import scala.concurrent.Future


class ApplicantTypeRepositoryImpl extends ApplicantTypeRepository{
  
  override def saveEntity(entity: ApplicantType): Future[Boolean] = {
    ApplicantTypeDatabase.ApplicantTypeTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[ApplicantType]] = {
    ApplicantTypeDatabase.ApplicantTypeTable.getEntities
  }

  override def getEntity(applicantTypeId: String): Future[Option[ApplicantType]] = {
    ApplicantTypeDatabase.ApplicantTypeTable.getEntity(applicantTypeId)
  }

  override def deleteEntity(entity: ApplicantType): Future[Boolean] = {
    ApplicantTypeDatabase.ApplicantTypeTable.deleteEntity(entity.applicantTypeId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    ApplicantTypeDatabase.ApplicantTypeTable.create.ifNotExists().future().map(result => result.head.isExhausted())
  }
}

class ApplicantTypeDatabase(override  val connector: KeySpaceDef) extends  Database[ApplicantTypeDatabase](connector){
  object ApplicantTypeTable extends ApplicantTypeTableImpl with connector.Connector
}

object ApplicantTypeDatabase extends ApplicantTypeDatabase(DataConnection.connector)
