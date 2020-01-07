package services.users.Impl

import domain.users.UserMatricInstitution
import repository.users.UserMatricInstitutionRepository
import services.users.{UserMatricInstitutionService, UserMatricSubjectService}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserMatricInstitutionServiceImpl extends UserMatricInstitutionService{
  override def saveEntity(entity: UserMatricInstitution): Future[Option[UserMatricInstitution]] =
    UserMatricInstitutionRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[UserMatricInstitution]] =
    UserMatricInstitutionRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[UserMatricInstitution]] =
    UserMatricInstitutionRepository.apply.getEntity(id)

  override def deleteEntity(entity: UserMatricInstitution): Future[Boolean] =
    UserMatricInstitutionRepository.apply.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserMatricInstitutionRepository.apply.createTable

  override def updateEntity(entity: UserMatricInstitution): Future[Option[UserMatricInstitution]] =
    for {
      userMatricSubjects <- UserMatricSubjectService.apply.getEntitiesForUser(entity.userId)
      updated <- if (userMatricSubjects.length > 0) Future(Some(entity))
      else UserMatricInstitutionRepository.apply.saveEntity(entity)
    } yield updated
}
