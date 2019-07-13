package services.users.Impl

import domain.users.UserResults
import repository.users.UserResultsRepository
import services.users.UserResultsService

import scala.concurrent.Future

class UserResultsServiceImpl extends UserResultsService{

  override def saveEntity(entity: UserResults): Future[Boolean] =
    UserResultsRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserResults]] =
    UserResultsRepository.roach.getEntities

  override def getEntity(userResultsId: String): Future[Option[UserResults]] =
    UserResultsRepository.roach.getEntity(userResultsId)

  override def deleteEntity(entity: UserResults): Future[Boolean] =
    UserResultsRepository.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserResultsRepository.roach.createTable

}
