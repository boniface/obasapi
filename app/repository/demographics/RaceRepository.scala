package repository.demographics

import domain.demographics.Race
import repository.Repository
import repository.demographics.impl.cockcroachdb.RaceRepositoryImpl

trait RaceRepository extends Repository [Race]{

}
object RaceRepository{
  def roach: RaceRepository = new RaceRepositoryImpl()
}
