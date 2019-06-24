package repository.institutions

import domain.institutions.School
import repository.Repository
import repository.institutions.Impl.cassandra.SchoolRepositoryImpl

trait SchoolRepository extends Repository[School] {

}

object SchoolRepository{
  def apply: SchoolRepository = new SchoolRepositoryImpl()
}
