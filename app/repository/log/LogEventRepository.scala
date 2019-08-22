package repository.log

import domain.log.LogEvent
import repository.Repository
import repository.log.Impl.cockroachdb.LogEventRepositoryImpl

trait LogEventRepository extends Repository[LogEvent]{

}

object LogEventRepository{
  def roach: LogEventRepository = new LogEventRepositoryImpl()
}

