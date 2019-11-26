//package controllers.academics
//
//import controllers.ApiResponse
//import domain.academics.CourseSubject
//import javax.inject.Inject
//import play.api.libs.json.{JsValue, Json}
//import play.api.{Logger, Logging}
//import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
//import services.academics.CourseSubjectService
//
//import scala.concurrent.Future
//
//class CourseSubjectController @Inject()
//(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) with Logging {
//
//  type DomainObject = CourseSubject
//
//  def className: String = "CourseSubjectController"
//  override val logger: Logger = Logger(className)
//  def domainService: CourseSubjectService = CourseSubjectService.apply
//
//  def create: Action[JsValue] = Action.async(parse.json) {
//    implicit request: Request[JsValue] =>
//      val entity = Json.fromJson[DomainObject](request.body).asEither
//      entity match {
//        case Right(value) =>
//          logger.info("Saving province district: " + value)
//          val response: Future[Option[DomainObject]] = for {
//            results: Option[DomainObject] <- domainService.saveEntity(value)
//          } yield results
//          api.requestResponse[Option[DomainObject]](response, className)
//        case Left(error) => api.errorResponse(error, className)
//      }
//  }
//
//  def getDistrictsInProvince(subjectId: String): Action[AnyContent] = Action.async {
//    implicit request: Request[AnyContent] =>
//      logger.info("Retrieve by subjectId: " + subjectId)
//      println("Retrieve by subjectId: " + subjectId)
//      val response: Future[Seq[DomainObject]] = for {
//        results <- domainService.getEntitiesForCourse(subjectId)
//      } yield results
//      api.requestResponse[Seq[DomainObject]](response, className)
//  }
//
//  def getProvinceForDistrict(courseId: String): Action[AnyContent] = Action.async {
//    implicit request: Request[AnyContent] =>
//      logger.info("Retrieve province by courseId: " + courseId)
//      println("Retrieve province by courseId: " + courseId)
//      val response: Future[Option[DomainObject]] = for {
//        results <- domainService.getEntities
//      } yield results.filter(value => value.courseId == courseId).headOption
//      api.requestResponse[Option[DomainObject]](response, className)
//  }
//
//  def read(subjectId: String, courseId: String): Action[AnyContent] = Action.async {
//    implicit request: Request[AnyContent] =>
//      logger.info("Retrieve by subjectId: " + subjectId + " and courseId: " + courseId)
//      println("Retrieve by subjectId: " + subjectId + " and courseId: " + courseId)
//      val response: Future[Option[DomainObject]] = for {
//        results <- domainService.getEntity(subjectId, courseId)
//      } yield results
//      api.requestResponse[Option[DomainObject]](response, className)
//  }
//
//  def getAll: Action[AnyContent] = Action.async {
//    implicit request: Request[AnyContent] =>
//      logger.info("Retrieve all requested")
//      println("Retrieve all requested")
//      val response: Future[Seq[DomainObject]] = for {
//        results <- domainService.getEntities
//      } yield results
//      api.requestResponse[Seq[DomainObject]](response, className)
//  }
//
//  def delete: Action[JsValue] = Action.async(parse.json) {
//    implicit request: Request[JsValue] =>
//      val entity = Json.fromJson[DomainObject](request.body).asEither
//      logger.info("Delete request with body: " + entity)
//      println("Delete request with body: " + entity)
//      entity match {
//        case Right(value) =>
//          val response: Future[Boolean] = for {
//            results: Boolean <- domainService.deleteEntity(value)
//          } yield results
//          api.requestResponse[Boolean](response, className)
//        case Left(error) => api.errorResponse(error, className)
//      }
//  }
//
//}
