package repository.application

import domain.application.ApplicationStatus
import repository.Repository
import repository.application.Impl.cassandra.ApplicationStatusRepositoryImpl

trait ApplicationStatusRepository extends Repository[ApplicationStatus]{

}
object ApplicationStatusRepository{
  def apply: ApplicationStatusRepository = new ApplicationStatusRepositoryImpl()
}