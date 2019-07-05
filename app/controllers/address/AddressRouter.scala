package controllers.address

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class AddressRouter@Inject ()
(addressTypeController: AddressTypeController,
 contactTypeController: ContactTypeController)extends SimpleRouter{
  override def routes: Routes ={

    //ADDRESSTYPE
    case GET(p"/addr/all") =>
      addressTypeController.getAllAddressType
    case GET(p"/addr/get/$addressTypeID") =>
      addressTypeController.getAddressTypeById(addressTypeID)
    case POST(p"/addr/create") =>
      addressTypeController.create
    case POST(p"/addr/update") =>
      addressTypeController.update
    case POST(p"/addr/delete") =>
      addressTypeController.deleteAddressType

      //CONTACT
    case GET(p"/cont/all") =>
      contactTypeController.getAllContactType
    case GET(p"/cont/get/$contactTypeId") =>
      contactTypeController.getContactTypeById(contactTypeId)
    case POST(p"/cont/create") =>
      contactTypeController.create
    case POST(p"/cont/update") =>
      contactTypeController.update
    case POST(p"/cont/delete") =>
      contactTypeController.deleteContactType










  }
}
