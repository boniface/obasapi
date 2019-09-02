package repository.subjects

import domain.subjects.MatricSubjects
import repository.Repository
import repository.subjects.impl.cockcroachdb.MatricSubjectsRepositoryImpl

trait MatricSubjectsRepository extends Repository [MatricSubjects]{

}
object MatricSubjectsRepository{
  def roach: MatricSubjectsRepository = new MatricSubjectsRepositoryImpl()
}
