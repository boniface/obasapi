package repository.application

import domain.application.ApplicationType
import repository.Repository
import repository.application.impl.cockcroachdb.ApplicationTypeRepositoryImpl

trait ApplicationTypeRepository extends Repository[ApplicationType] {

}

object ApplicationTypeRepository {
  def apply: ApplicationTypeRepository = new ApplicationTypeRepositoryImpl()
}
