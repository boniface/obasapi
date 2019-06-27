package repository.application

import domain.application.ApplicationResult
import repository.Repository
import repository.application.Impl.cassandra.ApplicationResultRepositoryImpl

trait ApplicationResultRepository extends Repository [ApplicationResult]{


}
object ApplicationResultRepository{

  def apply: ApplicationResultRepository= new ApplicationResultRepositoryImpl()

}
