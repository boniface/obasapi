package repository.subjects.impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.subjects.MatricSubjects
import repository.subjects.impl.cassandra.tables.MatricSubjectsTableImpl
import repository.subjects.MatricSubjectsRepository
//import util.connections.DataConnection

import util.connections.DataConnection

import scala.concurrent.Future


class MatricSubjectsRepositoryImpl extends MatricSubjectsRepository {
  override def saveEntity(entity: MatricSubjects): Future[Boolean] = {
    MatricSubjectDatabase.MatricSubjectsTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[MatricSubjects]] = {
    MatricSubjectDatabase.MatricSubjectsTable.getEntities
  }

  override def getEntity(subjectCode: String): Future[Option[MatricSubjects]] = {
    MatricSubjectDatabase.MatricSubjectsTable.getEntity(subjectCode)
  }

  override def deleteEntity(entity: MatricSubjects): Future[Boolean] ={
    MatricSubjectDatabase.MatricSubjectsTable.deleteEntity(entity.subjectCode) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    MatricSubjectDatabase.MatricSubjectsTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}

class MatricSubjectDatabase(override val connector: KeySpaceDef) extends Database[MatricSubjectDatabase](connector)
{
  object MatricSubjectsTable extends MatricSubjectsTableImpl with connector.Connector
}

object MatricSubjectDatabase extends  MatricSubjectDatabase(DataConnection.connector)