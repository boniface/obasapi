package controllers.application

import javax.inject.Inject

import play.api.routing.sird._

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter

class ApplicationRouter @Inject()(applicationStatusController: ApplicationStatusController
                                 ,applicantTypeController: ApplicantTypeController
                                 ,applicationResultController: ApplicationResultController)
  extends SimpleRouter {
  override def routes: Routes = {



    //APPLICANTTYPE
    case GET(p"/type/all") =>
      applicantTypeController.getAllApplicantType
    case GET(p"/type/get/$applicantTypeId") =>
      applicantTypeController.getApplicantTypeById(applicantTypeId)
    case POST(p"/type/create") =>
      applicantTypeController.create
    case POST(p"/type/update") =>
      applicantTypeController.update
    case POST(p"/type/delete") =>
      applicantTypeController.deleteApplicantType

    //APPLICATIONRESULT
    case GET(p"/result/all") =>
      applicationResultController.getAllApplicationResult
    case GET(p"/result/get/$applicationResultId") =>
      applicationResultController.getApplicationResultById(applicationResultId)
    case POST(p"/result/create") =>
      applicationResultController.create
    case POST(p"/result/update") =>
      applicationResultController.update
    case POST(p"/result/delete") =>
      applicationResultController.deleteApplicationResult


      //APPLICATIONSTATUS
    case GET(p"/status/all") =>
      applicationStatusController.getAllApplicationStatus
    case GET(p"/status/get/$applicationStatusId") =>
      applicationStatusController.getApplicationStatusById(applicationStatusId)
    case POST(p"/status/create") =>
      applicationStatusController.create
    case POST(p"/status/update") =>
      applicationStatusController.update
    case POST(p"/status/delete") =>
      applicationStatusController.deleteApplicationStatus


  }
}
