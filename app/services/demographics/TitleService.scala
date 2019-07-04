package services.demographics

import domain.demographics.Title
import services.CrudService
import services.demographics.Impl.cockroachdb

trait TitleService extends CrudService[Title]{



}

object TitleService{

  def roach: TitleService = new cockroachdb.TitleServiceImpl()
}
