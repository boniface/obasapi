package repository.application

import domain.application.ApplicantType
import repository.Repository
import repository.application.Impl.ApplicantTypeRepositoryImpl

trait ApplicantTypeRepository extends Repository [ApplicantType]{



}
object ApplicantTypeRepository{

  def apply: ApplicantTypeRepositoryImpl = new ApplicantTypeRepositoryImpl()
}