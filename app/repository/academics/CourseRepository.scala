package repository.academics

import domain.academics.Course
import repository.Repository

trait CourseRepository extends Repository[Course]{

}

object CourseRepository {
  def apply: CourseRepository = new CourseRepositoryImpl()
}