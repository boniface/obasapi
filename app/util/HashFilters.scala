package util

import com.google.inject.Singleton
import javax.inject.Inject
import play.api.http.HttpFilters
import play.filters.cors.CORSFilter


@Singleton
class HashFilters @Inject()(corsFilter: CORSFilter) extends HttpFilters {
  def filters = Seq(corsFilter)
}
