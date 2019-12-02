package repository.demographics

import domain.demographics.Town
import repository.Repository
import repository.demographics.impl.cockcroachdb.TownRepositoryImpl

trait TownRepository extends Repository[Town]{

}
object TownRepository{
  def roach: TownRepository = new TownRepositoryImpl()
}
