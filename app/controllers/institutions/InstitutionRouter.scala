package controllers.institutions

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class InstitutionRouter @Inject()(
                                   institutionController: InstitutionController,
                                   institutionAddressController: InstitutionAddressController,
                                   institutionContactController: InstitutionContactController,
                                   institutionLocationController: InstitutionLocationController,
                                   institutionTypeController: InstitutionTypeController
                                 ) extends SimpleRouter {
  override def routes: Routes = {

    // INSTITUTION
    case GET(p"/all") =>
      institutionController.getAll
    case GET(p"/get/$institutionId") =>
      institutionController.read(institutionId)
    case POST(p"/create") =>
      institutionController.create
    case POST(p"/update") =>
      institutionController.update
    case POST(p"/delete") =>
      institutionController.delete

    // INSTITUTION_ADDRESS
    case POST(p"/address/create") =>
      institutionAddressController.create
    case GET(p"/address/get/$institutionId/$addressTypeId") =>
      institutionAddressController.read(institutionId, addressTypeId)
    case POST(p"/address/update") =>
      institutionAddressController.update
    case GET(p"/address/all") =>
      institutionAddressController.getAll
    case GET(p"/address/getaddresses/$institutionId") =>
      institutionAddressController.getInstitutionAdresses(institutionId)
    case POST(p"/address/delete") =>
      institutionAddressController.delete

    // INSTITUTION_CONTACT
    case POST(p"/contact/create") =>
      institutionContactController.create
    case GET(p"/contact/get/$institutionId/$contactTypeId") =>
      institutionContactController.read(institutionId, contactTypeId)
    case POST(p"/contact/update") =>
      institutionContactController.update
    case GET(p"/contact/all") =>
      institutionContactController.getAll
    case GET(p"/contact/getcontacts/$institutionId") =>
      institutionContactController.getInstitutionContacts(institutionId)
    case POST(p"/contact/delete") =>
      institutionContactController.delete

      // INSTITUTION_LOCATION
    case GET(p"/location/all") =>
      institutionLocationController.getAll
    case GET(p"/location/get/$institutionId") =>
      institutionLocationController.read(institutionId)
    case POST(p"/location/create") =>
      institutionLocationController.create
    case POST(p"/location/update") =>
      institutionLocationController.update
    case POST(p"/location/delete") =>
      institutionLocationController.delete

    // INSTITUTION_Type
    case GET(p"/type/all") =>
      institutionTypeController.getAll
    case GET(p"/type/get/$id") =>
      institutionTypeController.read(id)
    case POST(p"/type/create") =>
      institutionTypeController.create
    case POST(p"/type/update") =>
      institutionTypeController.update
    case POST(p"/type/delete") =>
      institutionTypeController.delete


  }

}
