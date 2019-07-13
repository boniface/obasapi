package repository.application

import domain.application.ApplicationResult
import repository.Repository
import repository.application.impl.cockcroachdb

trait ApplicationResultRepository extends Repository [ApplicationResult]{


}
object ApplicationResultRepository{

  def roach: ApplicationResultRepository= new cockcroachdb.ApplicationResultRepositoryImpl()

}
