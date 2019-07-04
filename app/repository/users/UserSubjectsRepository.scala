package repository.users

import domain.users.UserSubjects
import repository.Repository
import repository.users.Impl.cockroachdb

trait UserSubjectsRepository extends Repository[UserSubjects] {

}

object UserSubjectsRepository{
  def roach: UserSubjectsRepository = new cockroachdb.UserSubjectsRepositoryImpl()
}
