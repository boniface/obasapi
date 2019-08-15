package services.log

import domain.log.LogEvent
import services.CrudService
import services.log.impl.cockroachdb.LogEventServiceImpl

trait LogEventService  extends CrudService[LogEvent]{

}
object LogEventService{
  def roach: LogEventService = new LogEventServiceImpl()
}


