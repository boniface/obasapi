package repository.security

import domain.security.ApiKeys
import repository.Repository
import repository.security.Impl.cassandra.ApiKeysRepositoryImpl

trait ApiKeysRepository extends Repository[ApiKeys]{

}

object ApiKeysRepository{
  def apply: ApiKeysRepository = new ApiKeysRepositoryImpl()
}
