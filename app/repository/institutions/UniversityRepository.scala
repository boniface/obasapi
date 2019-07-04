package repository.institutions

import domain.institutions.University
import repository.Repository
import repository.institutions.Impl.cockroachdb


trait UniversityRepository extends Repository[University]{

}

object UniversityRepository{
  def roach: UniversityRepository = new cockroachdb.UniversityRepositoryImpl()
}
