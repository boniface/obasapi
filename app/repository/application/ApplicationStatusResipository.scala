package repository.application

import domain.application.ApplicationStatus
import repository.Repository
import repository.application.Impl.ApplicationStatusRepositoryImpl

trait ApplicationStatusResipository extends Repository[ApplicationStatus]{

}
object ApplicationStatusResipository{
  def apply: ApplicationStatusRepositoryImpl = new ApplicationStatusRepositoryImpl()
}