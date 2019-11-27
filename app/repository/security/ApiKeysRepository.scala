package repository.security

import domain.security.ApiKeys
import repository.Repository

trait ApiKeysRepository extends Repository[ApiKeys]{

}

object ApiKeysRepository{
  def apply: ApiKeysRepository = new ApiKeysRepositoryImpl()
}
