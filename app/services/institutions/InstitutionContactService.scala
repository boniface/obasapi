package services.institutions

import domain.institutions.InstitutionContact
import services.CrudService
import services.institutions.Impl.cockroachdb.InstitutionContactServiceImpl

import scala.concurrent.Future

trait InstitutionContactService extends CrudService[InstitutionContact] {

  def getEntity(id: String, contactTypeId: String): Future[Option[InstitutionContact]]

  def getEntitiesForInstitution(id: String): Future[Seq[InstitutionContact]]
}

object InstitutionContactService {
  def apply: InstitutionContactService = new InstitutionContactServiceImpl()
}
