package repository.institutions

import domain.institutions.School
import repository.Repository
import repository.institutions.Impl.cockroachdb

trait SchoolRepository extends Repository[School] {

}

object SchoolRepository{
  def roach: SchoolRepository = new cockroachdb.SchoolRepositoryImpl()
}
