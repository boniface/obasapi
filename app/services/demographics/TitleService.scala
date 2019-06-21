package services.demographics

import domain.demographics.Title
import services.CrudService
import services.demographics.Impl.TitleServiceImpl

trait TitleService extends CrudService[Title]{



}

object TitleService{

  def apply: TitleServiceImpl = new TitleServiceImpl()
}
