package services.users.Impl.cockroachdb

import domain.users.UserSubjects
import services.users.UserSubjectsService

import scala.concurrent.Future

class UserSubjectsServiceImpl extends UserSubjectsService {

  override def saveEntity(entity: UserSubjects): Future[Boolean] =
    UserSubjectsService.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserSubjects]] =
    UserSubjectsService.roach.getEntities

  override def getEntity(userAddressId: String): Future[Option[UserSubjects]] =
    UserSubjectsService.roach.getEntity(userAddressId)

  override def deleteEntity(entity: UserSubjects): Future[Boolean] =
    UserSubjectsService.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserSubjectsService.roach.createTable

}
