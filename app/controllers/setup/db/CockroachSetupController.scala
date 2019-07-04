package controllers.setup.db

import controllers.ApiResponse
import javax.inject.Inject
import play.api.mvc._
import services.setup.db.DBSetupService

import scala.concurrent.ExecutionContext.Implicits.global

class CockroachSetupController @Inject()
( api: ApiResponse,cc: ControllerComponents) extends AbstractController(cc) {

  def className: String = "CockroachSetupController"

  def roachService = DBSetupService.roachImpl

  def createTables: Action[AnyContent] = Action.async{
    implicit request: Request[AnyContent] =>
      val tablesCreation = roachService.createTables
      tablesCreation.map(result => Ok("Tables created: " + result))
        .recover{
          case exception: Exception => {
            println(exception.getMessage)
            InternalServerError(exception.getMessage)
          }
        }
  }

}
