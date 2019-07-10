package controllers.location

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class LocationRouter @Inject()
(locationController: LocationController,
 locationTypeController: LocationTypeController)extends SimpleRouter {
  override def routes: Routes = {




    //Location
    case GET(p"/location/all") =>
      locationController.getAllLocation
    case GET(p"/location/get/$locationId") =>
      locationController.getLocationById(locationId)
    case POST(p"/location/create") =>
      locationController.create
    case POST(p"/location/update") =>
      locationController.update
    case POST(p"/location/delete") =>
      locationController.deleteLocation

    //LocationType
    case GET(p"/type/all") =>
    locationTypeController.getAllLocationType
    case GET(p"/type/get/$locationTypeId") =>
      locationTypeController.getLocationTypeById(locationTypeId)
    case POST(p"/type/create") =>
      locationTypeController.create
    case POST(p"/type/update") =>
      locationTypeController.update
    case POST(p"/type/delete") =>
      locationTypeController.deleteLocationType







  }
}
