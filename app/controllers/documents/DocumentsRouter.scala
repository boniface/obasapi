package controllers.documents

import controllers.address.{AddressTypeController, ContactTypeController}
import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class DocumentsRouter@Inject()
(documentController: DocumentController,documentTypeController: DocumentTypeController
)extends SimpleRouter{
  override def routes:Routes ={

    //DOCUMENT
    case GET(p"/doc/all") =>
     documentController.getAllDocument
    case GET(p"/doc/get/$email") =>
      documentController.getDocumentById(email)
    case POST(p"/doc/create") =>
      documentController.create
    case POST(p"/doc/update") =>
      documentController.update
    case POST(p"/doc/delete") =>
      documentController.deleteDocument


    //DOCUMENTTYPE
    case GET(p"/docType/all") =>
      documentTypeController.getAllDocumentType
    case GET(p"/contact/get/$documentTypeId") =>
      documentTypeController.getDocumentTypeById(documentTypeId)
    case POST(p"/contact/create") =>
      documentTypeController.create
    case POST(p"/contact/update") =>
      documentTypeController.update
    case POST(p"/contact/delete") =>
      documentTypeController.deleteDocumentType




  }
}