package controllers.application

import javax.inject.Inject

import play.api.routing.sird._

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter

class ApplicationRouter @Inject()(
                                   applicationStatusController: ApplicationStatusController,
                                   applicantTypeController: ApplicantTypeController,
                                   applicationController: ApplicationController,
                                   applicationTypeController: ApplicationTypeController
                                 )
  extends SimpleRouter {
  override def routes: Routes = {

    // APPLICATION
    case GET(p"/all") =>
      applicationController.getAll
    case GET(p"/get/$id") =>
      applicationController.read(id)
    case POST(p"/create") =>
      applicationController.create
    case POST(p"/update") =>
      applicationController.update
    case POST(p"/delete") =>
      applicationController.delete

    //APPLICANT_TYPE
    case GET(p"/applicanttype/all") =>
      applicantTypeController.getAllApplicantType
    case GET(p"/applicanttype/get/$applicantTypeId") =>
      applicantTypeController.getApplicantTypeById(applicantTypeId)
    case POST(p"/applicanttype/create") =>
      applicantTypeController.create
    case POST(p"/applicanttype/update") =>
      applicantTypeController.update
    case POST(p"/applicanttype/delete") =>
      applicantTypeController.deleteApplicantType

    //APPLICATION_STATUS
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

    //APPLICATION_TYPE
    case GET(p"/type/all") =>
      applicationTypeController.getAll
    case GET(p"/type/get/$id") =>
      applicationTypeController.read(id)
    case POST(p"/type/create") =>
      applicationTypeController.create
    case POST(p"/type/update") =>
      applicationTypeController.update
    case POST(p"/type/delete") =>
      applicationTypeController.delete

  }
}
