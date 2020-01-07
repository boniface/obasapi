package repository.institutions

import domain.institutions.Institution
import repository.Repository
import repository.institutions.impl.cockroachdb.InstitutionRepositoryImpl

import scala.concurrent.Future

trait InstitutionRepository extends Repository[Institution]{
  def getEntitiesForInstitutionTypeId(institutionTypeId: String): Future[Seq[Institution]]
}

object InstitutionRepository {
  def apply: InstitutionRepository = new InstitutionRepositoryImpl()
}
