package repository.academics

import domain.academics.Subject
import repository.Repository
import repository.academics.impl.cockroach.SubjectRepositoryImpl

trait SubjectRepository extends Repository[Subject]{

}

object SubjectRepository {
  def apply: SubjectRepository = new SubjectRepositoryImpl()
}
