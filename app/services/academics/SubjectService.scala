package services.academics

import domain.academics.Subject
import services.CrudService
import services.academics.Impl.cockroachdb.SubjectServiceImpl

trait SubjectService extends CrudService[Subject]{

}

object SubjectService {
  def apply: SubjectService = new SubjectServiceImpl()
}
