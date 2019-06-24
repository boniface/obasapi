package repository.demographics

import domain.demographics.Race
import repository.Repository
import repository.demographics.Impl.cassandra.RaceRepositoryImpl

trait RaceRepository extends Repository [Race]{

}
object RaceRepository{
  def apply: RaceRepositoryImpl = new RaceRepositoryImpl()
}
