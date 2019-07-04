package repository.application

import domain.application.ApplicantType
import repository.Repository
import repository.application.Impl.cockcroachdb

trait ApplicantTypeRepository extends Repository [ApplicantType]{



}
object ApplicantTypeRepository{

  def apply: ApplicantTypeRepository= new cockcroachdb.ApplicantTypeRepositoryImpl()
}