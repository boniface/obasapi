package repository.academics

import domain.academics.Subject
import repository.Repository

trait SubjectRepository extends Repository[Subject]{

}

object SubjectRepository {
  def apply: SubjectRepository = new SubjectRepositoryImpl()
}
