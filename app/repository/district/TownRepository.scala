package repository.district

import domain.district.Town
import repository.Repository
import repository.district.impl.cockroachdb.TownRepositoryImpl

trait TownRepository extends Repository[Town]{

}
object TownRepository{
  def roach: TownRepository = new TownRepositoryImpl()
}
