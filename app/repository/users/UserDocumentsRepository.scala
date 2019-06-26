package repository.users

import domain.users.UserDocuments
import repository.Repository
import repository.users.Impl.cassandra.UserDocumentsRepositoryImpl

trait UserDocumentsRepository extends Repository[UserDocuments]{

}

object UserDocumentsRepository{
  def apply: UserDocumentsRepository = new UserDocumentsRepositoryImpl()
}
