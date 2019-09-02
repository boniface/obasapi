package controllers

import domain.log.LogEvent
import domain.util.events.Events
import domain.util.exeptions.TokenFailException
import io.circe.Encoder
import io.circe.syntax._
import javax.inject.Inject
import play.api.http.ContentTypes
import play.api.libs.json.{JsPath, JsonValidationError}
import play.api.mvc.{AbstractController, ControllerComponents, Result}
import services.log.LogEventService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ApiResponse @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def errorResponse(error: Seq[(JsPath, Seq[JsonValidationError])], className: String): Future[Status] = {
    Future successful {
      val log = LogEvent(eventName = Events.RESPONSE, eventType = className, message = error.seq.toString())
            LogEventService.apply.saveEntity(log)
      val message = error.seq.toString()
      if (message.contains("Future.filter predicate is not satisfied")) PreconditionFailed
      else InternalServerError
    }
  }

  def requestResponse[A: Encoder](response: Future[A], className: String): Future[Result] = {
    response.map(result =>
      Ok(result.asJson.noSpaces)
        .as(ContentTypes.JSON)
    ).recover {
      case exp: TokenFailException =>
        val log = LogEvent(eventName = Events.TOKENFAILED, eventType = className, message = exp.getMessage)
                LogEventService.apply.saveEntity(log)
        Unauthorized
      case exp: Exception =>
        val log = LogEvent(eventName = Events.RESPONSE, eventType = className, message = exp.getMessage)
                LogEventService.apply.saveEntity(log)
        InternalServerError
    }
  }

}