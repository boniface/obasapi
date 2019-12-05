package repository.util.generic

import domain.util.generic.GenericStatus
import repository.Repository
import repository.util.generic.impl.cockroach.GenericStatusRepositoryImpl

trait GenericStatusRepository extends Repository[GenericStatus]{

}
object GenericStatusRepository{
  def roach: GenericStatusRepository = new GenericStatusRepositoryImpl()
}