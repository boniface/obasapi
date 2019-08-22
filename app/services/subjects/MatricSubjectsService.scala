package services.subjects

import domain.subjects.MatricSubjects
import services.CrudService
import services.subjects.Impl.cockroachdb.MatricSubjectsServiceImpl

trait MatricSubjectsService extends CrudService[MatricSubjects]{

}

object MatricSubjectsService{
  def roach: MatricSubjectsService = new MatricSubjectsServiceImpl()
}
