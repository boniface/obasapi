package repository.demographics

import domain.demographics.Gender
import repository.Repository
import repository.demographics.impl.cockcroachdb.GenderRepositoryImpl

trait GenderRepository extends Repository [Gender]{

}
object GenderRepository{

  def roach: GenderRepository = new GenderRepositoryImpl()

}
