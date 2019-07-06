package repository.subjects

import domain.subjects.UniversityCourses
import repository.Repository
import repository.subjects.impl.cockcroachdb

trait UniversityCoursesRepository extends Repository [UniversityCourses]{

}
object UniversityCoursesRepository{
  def roach: UniversityCoursesRepository = new cockcroachdb.UniversityCoursesRepositoryImpl()
}