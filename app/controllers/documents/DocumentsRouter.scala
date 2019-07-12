package controllers.documents

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
    case GET(p"/type/all") =>
      documentTypeController.getAllDocumentType
    case GET(p"/type/get/$documentTypeId") =>
      documentTypeController.getDocumentTypeById(documentTypeId)
    case POST(p"/type/create") =>
      documentTypeController.create
    case POST(p"/type/update") =>
      documentTypeController.update
    case POST(p"/type/delete") =>
      documentTypeController.deleteDocumentType




  }
}