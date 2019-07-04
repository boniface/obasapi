package services.subjects

import domain.subjects.UniversityCourses
import services.CrudService
import services.subjects.Impl.UniversityCoursesServiceImpl

trait UniversityCoursesService extends CrudService[UniversityCourses]{

}

object UniversityCoursesService {
  def apply: UniversityCoursesService = new UniversityCoursesServiceImpl()
}
