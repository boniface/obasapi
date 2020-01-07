package services.institutions

import domain.institutions.InstitutionAddress
import services.CrudService
import services.institutions.Impl.cockroachdb.InstitutionAddressServiceImpl

import scala.concurrent.Future

trait InstitutionAddressService extends CrudService[InstitutionAddress] {

  def getEntity(id: String, addressTypeId: String): Future[Option[InstitutionAddress]]

  def getEntitiesForInstitution(id: String): Future[Seq[InstitutionAddress]]

}

object InstitutionAddressService {
  def apply: InstitutionAddressService = new InstitutionAddressServiceImpl()
}
