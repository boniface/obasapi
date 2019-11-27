package services.academics

import domain.academics.Course
import services.CrudService
import services.academics.Impl.cockroach.CourseServiceImpl

trait CourseService extends CrudService[Course]{

}

object CourseService {
  def apply: CourseService = new CourseServiceImpl()
}
