package repository.application

import domain.application.ApplicationStatus
import repository.Repository
import repository.application.Impl.cockcroachdb

trait ApplicationStatusRepository extends Repository[ApplicationStatus]{

}
object ApplicationStatusRepository{
  def roach: ApplicationStatusRepository = new cockcroachdb.ApplicationStatusRepositoryImpl()
}