package repository.application

import domain.application.ApplicantType
import repository.Repository
import repository.application.impl.cockcroachdb.ApplicantTypeRepositoryImpl

import scala.concurrent.Future

trait ApplicantTypeRepository extends Repository [ApplicantType]{
  def getEntityByName(name: String): Future[Option[ApplicantType]]
}

object ApplicantTypeRepository{
  def roach: ApplicantTypeRepository= new ApplicantTypeRepositoryImpl()
}