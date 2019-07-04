package repository.subjects

import domain.subjects.MatricSubjects
import repository.Repository
import repository.subjects.Impl.cockcroachdb

trait MatricSubjectsRepository extends Repository [MatricSubjects]{

}
object MatricSubjectsRepository{
  def apply: MatricSubjectsRepository = new cockcroachdb.MatricSubjectsRepositoryImpl()
}
