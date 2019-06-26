package repository.institutions

import domain.institutions.University
import repository.Repository
import repository.institutions.Impl.cassandra.UniversityRepositoryImpl


trait UniversityRepository extends Repository[University]{

}

object UniversityRepository{
  def apply: UniversityRepository = new UniversityRepositoryImpl()
}
