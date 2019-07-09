package services.demographics

import domain.demographics.Race
import services.CrudService
import services.demographics.Impl.cockroachdb


trait RaceService extends CrudService[Race]{

}

object RaceService{

  def roach: RaceService = new cockroachdb.RaceServiceImpl()
}
