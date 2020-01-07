package repository.application

import domain.application.Application
import repository.Repository
import repository.application.impl.cockcroachdb.ApplicationRepositoryImpl

trait ApplicationRepository extends Repository[Application] {

}

object ApplicationRepository {
  def apply: ApplicationRepository = new ApplicationRepositoryImpl()
}
