package repository.application

import domain.application.ApplicationResult
import repository.Repository
import repository.application.impl.cockcroachdb.ApplicationResultRepositoryImpl

trait ApplicationResultRepository extends Repository [ApplicationResult]{


}
object ApplicationResultRepository{

  def roach: ApplicationResultRepository= new ApplicationResultRepositoryImpl()

}
