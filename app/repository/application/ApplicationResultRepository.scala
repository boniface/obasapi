package repository.application

import domain.application.ApplicationResult
import repository.Repository
import repository.application.Impl.cockcroachdb

trait ApplicationResultRepository extends Repository [ApplicationResult]{


}
object ApplicationResultRepository{

  def apply: ApplicationResultRepository= new cockcroachdb.ApplicationResultRepositoryImpl()

}
