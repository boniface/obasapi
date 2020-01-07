package services.users.Impl

import domain.users.UserApplicationInstitution
import repository.users.UserApplicationInstitutionRepository
import services.users.{UserApplicationCourseService, UserApplicationInstitutionService}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserApplicationInstitutionServiceImpl extends UserApplicationInstitutionService {
  override def getEntity(userId: String, applicationId: String): Future[Option[UserApplicationInstitution]] =
    UserApplicationInstitutionRepository.apply.getEntity(userId, applicationId)

  override def getEntitiesForUser(userId: String): Future[Seq[UserApplicationInstitution]] =
    UserApplicationInstitutionRepository.apply.getEntitiesForUser(userId)

  override def saveEntity(entity: UserApplicationInstitution): Future[Option[UserApplicationInstitution]] =
    UserApplicationInstitutionRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[UserApplicationInstitution]] =
    UserApplicationInstitutionRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[UserApplicationInstitution]] = ???

  override def deleteEntity(entity: UserApplicationInstitution): Future[Boolean] =
    UserApplicationInstitutionRepository.apply.deleteEntity(entity)

  override def createTable: Future[Boolean] = UserApplicationInstitutionRepository.apply.createTable

  override def updateEntity(entity: UserApplicationInstitution): Future[Option[UserApplicationInstitution]] = {
    for {
      userApplicationCourse <- UserApplicationCourseService.apply.getEntity(entity.userId, entity.applicationId)
      updated <- if (userApplicationCourse.isEmpty) UserApplicationInstitutionRepository.apply.saveEntity(entity)
      else Future(Some(entity))
    } yield updated
  }
}
