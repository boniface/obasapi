package repository.subjects

import domain.subjects.UniversityCourses
import repository.Repository
import repository.subjects.impl.cockcroachdb.UniversityCoursesRepositoryImpl

trait UniversityCoursesRepository extends Repository [UniversityCourses]{

}
object UniversityCoursesRepository{
  def roach: UniversityCoursesRepository = new UniversityCoursesRepositoryImpl()
}