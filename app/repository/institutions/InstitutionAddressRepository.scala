package repository.institutions

import domain.institutions.InstitutionAddress
import repository.Repository
import repository.institutions.impl.cockroachdb.InstitutionAddressRepositoryImpl

import scala.concurrent.Future

trait InstitutionAddressRepository extends Repository[InstitutionAddress]{

  def getEntity(id: String, addressTypeId: String): Future[Option[InstitutionAddress]]

  def getEntitiesForInstitution(id: String): Future[Seq[InstitutionAddress]]

}

object InstitutionAddressRepository{
  def apply: InstitutionAddressRepository = new InstitutionAddressRepositoryImpl()
}
