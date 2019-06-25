package repository.users

import domain.users.UserSubjects
import repository.Repository
import repository.users.Impl.cassandra.UserSubjectsRepositoryImpl

trait UserSubjectsRepository extends Repository[UserSubjects] {

}

object UserSubjectsRepository{
  def apply: UserSubjectsRepository = new UserSubjectsRepositoryImpl()
}
