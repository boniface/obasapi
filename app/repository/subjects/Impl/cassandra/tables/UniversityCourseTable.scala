package repository.subjects.Impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.subjects.UniversityCourses

import scala.concurrent.Future


abstract class UniversityCourseTable extends Table[UniversityCourseTable,UniversityCourses]{

  object courseCode extends StringColumn with PartitionKey

  object description extends OptionalStringColumn

  object name extends StringColumn

  object Type extends StringColumn

  object Term extends StringColumn
  
}

abstract class UniversityCoursesTableImpl extends UniversityCourseTable with RootConnector{

  override  lazy val tableName ="UniversityCourses"


  def saveEntity(entity:UniversityCourses): Future[ResultSet] ={
    insert
      .value(_.courseCode, entity.courseCode)
      .value(_.description, entity.description)
      .value(_. name, entity. name)
      .value(_.Type, entity.Type)
      .value(_.Term, entity.Term)
      .future()

  }

  def getEntity(courseCode: String): Future[Option[UniversityCourses]] = {
    select
      .where(_.courseCode eqs courseCode)
      .one()
  }

  def getEntities: Future[Seq[UniversityCourses]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(courseCode: String): Future[ResultSet] = {
    delete
      .where(_.courseCode eqs courseCode)
      .future()
  }


}
