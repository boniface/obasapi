package controllers.users

import controllers.application.ApplicationStatusController
import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class UserRouter @Inject()
(userAddressController: UserAddressController, userApplicationController: UserApplicationController,
 userCommunicationController: UserCommunicationController, userContactsController: UserContactsController,
 userController: UserController, userDemographicsController: UserDemographicsController, userDocumentsController: UserDocumentController,
 userInstitutionController: UserInstitutionController, userPasswordController: UserPasswordController,
 userRelativeController: UserRelativeController, userResultsController: UserResultsController,
 userRoleController: UserRoleController, userSubjectController: UserSubjectController,
 userTownController: UserTownController, userCourseController: UserCourseController
) extends SimpleRouter {
  override def routes: Routes = {
    //USER
    case GET(p"/all") =>
      userController.getAllUser
    case GET(p"/get/$email") =>
      userController.getUserById(email)
    case POST(p"/create") =>
      userController.create
    case POST(p"/update") =>
      userController.update
    case POST(p"/delete") =>
      userController.deleteUser

    //ADDRESS
    case GET(p"/address/all") =>
      userAddressController.getAllUserAddress
    case GET(p"/address/get/$userId") =>
      userAddressController.getUserAddresses(userId)
    case GET(p"/address/get/$userId/$addressTypeId") =>
      userAddressController.getUserAddress(userId, addressTypeId)
    case POST(p"/address/create") =>
      userAddressController.create
    case POST(p"/address/update") =>
      userAddressController.update
    case POST(p"/address/delete") =>
      userAddressController.deleteUserAddress

    // USER_APPLICATION
    case GET(p"/application/all/$userId") =>
      userApplicationController.getEntitiesForUser(userId)
    case GET(p"/application/get/$userId/$applicationId") =>
      userApplicationController.read(userId, applicationId)
    case GET(p"/application/latest/$userId") =>
      userApplicationController.getLatestForUser(userId)
    case POST(p"/application/create") =>
      userApplicationController.create
    case POST(p"/application/delete") =>
      userApplicationController.delete

    //COMMUNICATION
    case GET(p"/communication/all") =>
      userCommunicationController.getAllUserCommunication
    case GET(p"/communication/get/$communicationId") =>
      userCommunicationController.getUserCommunicationById(communicationId)
    case POST(p"/communication/create") =>
      userCommunicationController.create
    case POST(p"/communication/update") =>
      userCommunicationController.update
    case POST(p"/communication/delete") =>
      userCommunicationController.deleteUserCommunication

    //CONTACTS
    case GET(p"/contacts/all") =>
      userContactsController.getAllUserContacts
    case GET(p"/contacts/get/$userId/$contactTypeId") =>
      userContactsController.getUserContactsById(userId, contactTypeId)
    case GET(p"/contacts/get/$userId") =>
      userContactsController.getUserContacts(userId)
    case POST(p"/contacts/create") =>
      userContactsController.create
    case POST(p"/contacts/update") =>
      userContactsController.update
    case POST(p"/contacts/delete") =>
      userContactsController.deleteUserContacts

    //DEMOGRAPHICS
    case GET(p"/demographics/all") =>
      userDemographicsController.getAllUserDemographics
    case GET(p"/demographics/get/$userDemographicsId") =>
      userDemographicsController.getUserDemographicsById(userDemographicsId)
    case POST(p"/demographics/create") =>
      userDemographicsController.create
    case POST(p"/demographics/update") =>
      userDemographicsController.update
    case POST(p"/demographics/delete") =>
      userDemographicsController.deleteUserDemographics

    //DOCUMENTS
    case GET(p"/documents/all") =>
      userDocumentsController.getAllUsersDocuments
    case GET(p"/documents/get/$userId") =>
      userDocumentsController.getUserDocuments(userId)
    case GET(p"/documents/get/$userId/$documentId") =>
      userDocumentsController.getUserDocument(userId, documentId)
    case POST(p"/documents/create") =>
      userDocumentsController.create
    case POST(p"/documents/update") =>
      userDocumentsController.update
    case POST(p"/documents/delete") =>
      userDocumentsController.deleteUserDocuments

    //INSTITUTION
    case GET(p"/institution/all") =>
      userInstitutionController.getAllUserInstitution
    case GET(p"/institution/get/$userInstitutionId") =>
      userInstitutionController.getUserInstitutionById(userInstitutionId)
    case POST(p"/institution/create") =>
      userInstitutionController.create
    case POST(p"/institution/update") =>
      userInstitutionController.update
    case POST(p"/institution/delete") =>
      userInstitutionController.deleteUserInstitution

    //PASSWORD
    /** Strange block starts here
     * This is not supposed to be here */
    //    case GET(p"/password/all") =>
    //      userPasswordController.getAllUserPassword
    //    case GET(p"/password/get/$userPasswordId") =>
    //      userPasswordController.getUserPasswordById(userPasswordId)
    //    case POST(p"/password/create") =>
    //      userPasswordController.create
    //    case POST(p"/password/update") =>
    //      userPasswordController.update
    //    case POST(p"/password/delete") =>
    //      userPasswordController.deleteUserPassword
    /** Strange block ends here */

    //RELATIVE
    case GET(p"/relative/all") =>
      userRelativeController.getAllUserRelative
    case GET(p"/relative/get/$userId") =>
      userRelativeController.getUserRelativeById(userId)
    case POST(p"/relative/create") =>
      userRelativeController.create
    case POST(p"/relative/update") =>
      userRelativeController.update
    case POST(p"/relative/delete") =>
      userRelativeController.deleteUserRelative

    //RESULTS
    case GET(p"/results/all") =>
      userResultsController.getAllUserResults
    case GET(p"/results/get/$userResultsId") =>
      userResultsController.getUserResultsById(userResultsId)
    case POST(p"/results/create") =>
      userResultsController.create
    case POST(p"/results/update") =>
      userResultsController.update
    case POST(p"/results/delete") =>
      userResultsController.deleteUserResults

    //ROLE
    case GET(p"/role/all") =>
      userRoleController.getAllUserRole
    case GET(p"/role/get/$userRoleId") =>
      userRoleController.getUserRoleById(userRoleId)
    case POST(p"/role/create") =>
      userRoleController.create
    case POST(p"/role/update") =>
      userRoleController.update
    case POST(p"/role/delete") =>
      userRoleController.deleteUserRole

    //SUBJECTS
    case GET(p"/subject/all/$userId") =>
      userSubjectController.getSubjectsForUser(userId)
    case GET(p"/subject/get/$userId/$institutionId/$subjectId") =>
      userSubjectController.read(userId, institutionId, subjectId)
    case GET(p"/subject/getforinstitution/$userId/$institutionId") =>
      userSubjectController.getSubjectsForUserPerInstitution(userId, institutionId)
    case POST(p"/subject/create") =>
      userSubjectController.create
    case POST(p"/subject/update") =>
      userSubjectController.update
    case POST(p"/subject/delete") =>
      userSubjectController.delete

    // USER_TOWN
    case POST(p"/town/create") =>
      userTownController.create
    case GET(p"/town/get/$userId") =>
      userTownController.read(userId)
    case POST(p"/town/update") =>
      userTownController.update
    case GET(p"/town/delete") =>
      userTownController.delete

    // COURSE
    case GET(p"/course/all/$userId") =>
      userCourseController.getCoursesForUser(userId)
    case GET(p"/course/getforinstitution/$userId/$institutionId") =>
      userCourseController.getCoursesForUserPerInstitution(userId, institutionId)
    case GET(p"/course/get/$userId/$institutionId/$courseId") =>
      userCourseController.read(userId, institutionId, courseId)
    case POST(p"/course/create") =>
      userCourseController.create
    case POST(p"/course/delete") =>
      userCourseController.delete

  }
}





