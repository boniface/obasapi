package repository.users

import domain.users.UserDocuments
import repository.Repository
import repository.users.impl.cockroachdb.UserDocumentsRepositoryImpl

trait UserDocumentsRepository extends Repository[UserDocuments]{

}

object UserDocumentsRepository{
  def roach: UserDocumentsRepository = new UserDocumentsRepositoryImpl()
}
