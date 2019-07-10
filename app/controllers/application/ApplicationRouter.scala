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
    case GET(p"/atype/all") =>
      applicantTypeController.getAllApplicantType
    case GET(p"/atype/get/$applicantTypeId") =>
      applicantTypeController.getApplicantTypeById(applicantTypeId)
    case POST(p"/atype/create") =>
      applicantTypeController.create
    case POST(p"/atype/update") =>
      applicantTypeController.update
    case POST(p"/atype/delete") =>
      applicantTypeController.deleteApplicantType

    //APPLICATIONRESULT
    case GET(p"/aresult/all") =>
      applicationResultController.getAllApplicationResult
    case GET(p"/aresult/get/$applicationResultId") =>
      applicationResultController.getApplicationResultById(applicationResultId)
    case POST(p"/aresult/create") =>
      applicationResultController.create
    case POST(p"/aresult/update") =>
      applicationResultController.update
    case POST(p"/aresult/delete") =>
      applicationResultController.deleteApplicationResult


      //APPLICATIONSTATUS
    case GET(p"/astatus/all") =>
      applicationStatusController.getAllApplicationStatus
    case GET(p"/astatus/get/$applicationStatusId") =>
      applicationStatusController.getApplicationStatusById(applicationStatusId)
    case POST(p"/astatus/create") =>
      applicationStatusController.create
    case POST(p"/astatus/update") =>
      applicationStatusController.update
    case POST(p"/astatus/delete") =>
      applicationStatusController.deleteApplicationStatus


  }
}
