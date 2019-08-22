package repository.demographics

import domain.demographics.Title
import repository.Repository
import repository.demographics.impl.cockcroachdb.TitleRepositoryImpl

trait TitleRepository extends Repository [Title]{

}

object TitleRepository{

  def roach: TitleRepository = new TitleRepositoryImpl()

}
