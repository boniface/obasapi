package repository.application

import domain.application.ApplicationResult
import repository.Repository
import repository.application.Impl.ApplicationResultRepositoryImpl

trait ApplicationResultRepository extends Repository [ApplicationResult]{


}
object ApplicationResultRepository{

  def apply: ApplicationResultRepositoryImpl = new ApplicationResultRepositoryImpl()

}
