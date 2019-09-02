package repository.security

import domain.security.ApiKeys
import repository.Repository
import repository.security.impl.cockcroachdb.ApiKeysRepositoryImpl

trait ApiKeysRepository extends Repository[ApiKeys]{

}

object ApiKeysRepository{
  def apply: ApiKeysRepository = new ApiKeysRepositoryImpl()
}
