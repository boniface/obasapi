package repository.subjects

import domain.subjects.UniversityCourses
import repository.Repository
import repository.subjects.Impl.cockcroachdb

trait UniversityCoursesRepository extends Repository [UniversityCourses]{

}
object UniversityCoursesRepository{
  def roach: UniversityCoursesRepository = new cockcroachdb.UniversityCoursesRepositoryImpl()
}