package repository.subjects.impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.subjects.UniversityCourses
import repository.subjects.impl.cassandra.tables.UniversityCoursesTableImpl
//.mail.Impl.cassandra.tables.SmtpConfigTableImpl
import repository.subjects.UniversityCoursesRepository
//.mail.SmtpConfigRepository
//import util.connections.DataConnection
import util.connections.DataConnection

import scala.concurrent.Future

class UniversityCoursesRepositoryImpl extends UniversityCoursesRepository {
  override def saveEntity(entity: UniversityCourses) = ???

  override def getEntities: Future[Seq[UniversityCourses]] = {
    UniversityCoursesDatabase.UniversityCoursesTable.getEntities
  }

  override def getEntity(courseCode: String): Future[Option[UniversityCourses]] = {
    UniversityCoursesDatabase.UniversityCoursesTable.getEntity(courseCode)
  }

  override def deleteEntity(entity: UniversityCourses): Future[Boolean] = {
    UniversityCoursesDatabase.UniversityCoursesTable.deleteEntity(entity.courseCode) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    UniversityCoursesDatabase.UniversityCoursesTable.create.ifNotExists().future().map(result => result.head.isExhausted())
  }
}

class UniversityCoursesDatabase(override val connector: KeySpaceDef)extends Database[UniversityCoursesDatabase](connector){
  object UniversityCoursesTable extends UniversityCoursesTableImpl with connector.Connector
}

object UniversityCoursesDatabase extends UniversityCoursesDatabase(DataConnection.connector)