package repository.demographics

import domain.demographics.Gender
import repository.Repository
import repository.demographics.Impl.GenderRepositoryImpl

trait GenderRepository extends Repository [Gender]{

}
object GenderRepository{

  def apply: GenderRepositoryImpl = new GenderRepositoryImpl()

}
