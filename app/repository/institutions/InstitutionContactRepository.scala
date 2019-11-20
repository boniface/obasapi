package repository.institutions

import domain.institutions.InstitutionContact
import repository.Repository
import repository.institutions.impl.cockroachdb.InstitutionContactRepositoryImpl

import scala.concurrent.Future

trait InstitutionContactRepository extends Repository[InstitutionContact]{

  def getEntity(id: String, contactTypeId: String): Future[Option[InstitutionContact]]

  def getEntitiesForInstitution(id: String): Future[Seq[InstitutionContact]]

}

object InstitutionContactRepository {
  def apply: InstitutionContactRepository = new InstitutionContactRepositoryImpl()
}
