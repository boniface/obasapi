package repository.demographics

import domain.demographics.Gender
import repository.Repository
import repository.demographics.Impl.cassandra.GenderRepositoryImpl

trait GenderRepository extends Repository [Gender]{

}
object GenderRepository{

  def apply: GenderRepository = new GenderRepositoryImpl()

}
