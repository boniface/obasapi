package repository.demographics

import domain.demographics.Title
import repository.Repository
import repository.demographics.Impl.cassandra.TitleRepositoryImpl

trait TitleRepository extends Repository [Title]{

}

object TitleRepository{

  def apply: TitleRepositoryImpl = new TitleRepositoryImpl()

}
