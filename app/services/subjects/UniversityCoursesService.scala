package services.subjects

import domain.subjects.UniversityCourses
import services.CrudService
import services.subjects.Impl.cockroachdb

trait UniversityCoursesService extends CrudService[UniversityCourses]{

}

object UniversityCoursesService {
  def roach: UniversityCoursesService = new cockroachdb.UniversityCoursesServiceImpl()
}
