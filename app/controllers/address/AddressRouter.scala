package controllers.address

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class AddressRouter @Inject()
(addressTypeController: AddressTypeController,
 contactTypeController: ContactTypeController) extends SimpleRouter {
  override def routes: Routes = {

    //ADDRESSTYPE
    case GET(p"/address/get/all ") =>
      addressTypeController.getAllAddressType
    case GET(p"/address/get/$addressTypeID ") =>
      addressTypeController.getAddressTypeById(addressTypeID)
    case POST(p"/address/create ") =>
      addressTypeController.create
    case POST(p"/address/update ") =>
      addressTypeController.update
    case POST(p"/address/delete ") =>
      addressTypeController.deleteAddressType

    //CONTACT
    case GET(p"/contact/all ") =>
      contactTypeController.getAllContactType
    case GET(p"/contact/get/$contactTypeId ") =>
      contactTypeController.getContactTypeById(contactTypeId)
    case POST(p"/contact/create ") =>
      contactTypeController.create
    case POST(p"/contact/update ") =>
      contactTypeController.update
    case POST(p"/contact/delete ") =>
      contactTypeController.deleteContactType
  }
}
