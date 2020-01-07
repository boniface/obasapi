package services.institutions

import domain.institutions.Institution
import services.CrudService
import services.institutions.Impl.cockroachdb.InstitutionServiceImpl

import scala.concurrent.Future

trait InstitutionService extends CrudService[Institution] {
  def getEntitiesForInstitutionTypeId(institutionTypeId: String): Future[Seq[Institution]]
}

object InstitutionService {
  def apply: InstitutionService = new InstitutionServiceImpl()
}
