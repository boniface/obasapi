package services.demographics

import domain.demographics.Race
import services.CrudService
import services.demographics.Impl.RaceServiceImpl

trait RaceService extends CrudService[Race]{


}

object RaceService{

  def apply: RaceService = new RaceServiceImpl()
}