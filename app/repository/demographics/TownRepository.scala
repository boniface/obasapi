package repository.demographics

import domain.demographics.Town
import repository.Repository
import repository.demographics.impl.cockcroachdb.TownRepositoryImpl

@Deprecated
trait TownRepository extends Repository[Town]{

}

@Deprecated
object TownRepository{
  def roach: TownRepository = new TownRepositoryImpl()
}
