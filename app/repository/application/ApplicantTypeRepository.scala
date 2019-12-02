package repository.application

import domain.application.ApplicantType
import repository.Repository
import repository.application.impl.cockcroachdb.ApplicantTypeRepositoryImpl

trait ApplicantTypeRepository extends Repository [ApplicantType]{



}
object ApplicantTypeRepository{

  def roach: ApplicantTypeRepository= new ApplicantTypeRepositoryImpl()
}