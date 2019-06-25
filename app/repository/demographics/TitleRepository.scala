package repository.demographics

import domain.demographics.Title
import repository.Repository
import repository.demographics.Impl.TitleRepositoryImpl

trait TitleRepository extends Repository [Title]{

}

object TitleRepository{

  def apply: TitleRepositoryImpl = new TitleRepositoryImpl()

}
