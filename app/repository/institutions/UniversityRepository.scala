package repository.institutions

import domain.institutions.University
import repository.Repository
import repository.institutions.impl.cockroachdb.UniversityRepositoryImpl


trait UniversityRepository extends Repository[University]{

}

object UniversityRepository{
  def roach: UniversityRepository = new UniversityRepositoryImpl()
}
