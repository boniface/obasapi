package repository.subjects.Impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.subjects.MatricSubjects

import scala.concurrent.Future


abstract class MatricSubjectsTable extends Table[MatricSubjectsTable, MatricSubjects]{

  object subjectCode extends StringColumn with PartitionKey

  object description extends OptionalStringColumn

  object name extends StringColumn

  object Term extends StringColumn
  
}

abstract class MatricSubjectsTableImpl extends MatricSubjectsTable with RootConnector{

  override  lazy val tableName ="MatricSubjects"


  def saveEntity(entity:MatricSubjects): Future[ResultSet] ={
    insert
      .value(_.subjectCode, entity.subjectCode)
      .value(_.description, entity.description)
      .value(_.name, entity.name)
      .value(_.Term , entity.Term )
      .future()

  }

  def getEntity(subjectCode: String): Future[Option[MatricSubjects]] = {
    select
      .where(_.subjectCode eqs subjectCode)
      .one()
  }

  def getEntities: Future[Seq[MatricSubjects]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(subjectCode: String): Future[ResultSet] = {
    delete
      .where(_.subjectCode eqs subjectCode)
      .future()
  }


}