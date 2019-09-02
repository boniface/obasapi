package services.users

import domain.users.UserDocuments
import services.CrudService
import services.users.Impl.UserDocumentsServiceImpl

trait UserDocumentsService extends CrudService[UserDocuments]{

}

object UserDocumentsService{
  def roach: UserDocumentsService = new UserDocumentsServiceImpl()
}
