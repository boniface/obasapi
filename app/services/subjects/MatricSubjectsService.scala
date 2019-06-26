package services.subjects

import domain.subjects.MatricSubjects
import services.CrudService
import services.subjects.Impl.MatricSubjectsServiceImpl

trait MatricSubjectsService extends CrudService[MatricSubjects]{

}

object MatricSubjectsService{
  def apply: MatricSubjectsService = new MatricSubjectsServiceImpl()
}
