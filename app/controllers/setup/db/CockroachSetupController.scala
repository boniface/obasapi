package controllers.setup.db

import controllers.ApiResponse
import javax.inject.Inject
import play.api.mvc._
import services.setup.db.{DBSetupService, LookupSetupService}

import scala.concurrent.ExecutionContext.Implicits.global

class CockroachSetupController @Inject()
( api: ApiResponse,cc: ControllerComponents) extends AbstractController(cc) {

  def className: String = "CockroachSetupController"

  def dBSetupService: DBSetupService = DBSetupService.roachImpl
  def lookupSetupService: LookupSetupService = LookupSetupService.apply

  def createTables: Action[AnyContent] = Action.async{
    implicit request: Request[AnyContent] =>
      val tablesCreation = dBSetupService.createTables
      tablesCreation.map(result => Ok("Tables created: " + result))
        .recover{
          case exception: Exception => {
            println(exception.getMessage)
            InternalServerError(exception.getMessage)
          }
        }
  }

  def loadData: Action[AnyContent] = Action.async{
    implicit request: Request[AnyContent] =>
      val loadDataResponse = lookupSetupService.loadLookupData
      loadDataResponse.map(result => Ok("lookup data loaded: " + result))
        .recover{
          case exception: Exception => {
            println(exception.getMessage)
            InternalServerError(exception.getMessage)
          }
        }
  }

}
