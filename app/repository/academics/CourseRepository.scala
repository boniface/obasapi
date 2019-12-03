package repository.academics

import domain.academics.Course
import repository.Repository
import repository.academics.impl.cockroach.CourseRepositoryImpl

trait CourseRepository extends Repository[Course]{

}

object CourseRepository {
  def apply: CourseRepository = new CourseRepositoryImpl()
}