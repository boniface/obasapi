package controllers.users

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class UserRouter @Inject()
(userAddressController: UserAddressController,
 userApplicationResultController: UserApplicationResultController,
 userCommunicationController: UserCommunicationController,
 userContactsController: UserContactsController,
 userController: UserController,
 userDemographicsController: UserDemographicsController,
 userDocumentsController: UserDocumentsController,
 userInstitutionController: UserInstitutionController,
 userPasswordController: UserPasswordController,
 userRelativeController: UserRelativeController,
 userResultsController: UserResultsController,
 userRoleController: UserRoleController,
 userSubjectsController: UserSubjectsController) extends SimpleRouter {
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

    //APPLICATION
    case GET(p"/application/all") =>
      userApplicationResultController.getAllUserApplicationResult
    case GET(p"/application/get/$userApplicationResultId") =>
      userApplicationResultController.getUserApplicationResultById(userApplicationResultId)
    case POST(p"/application/create") =>
      userApplicationResultController.create
    case POST(p"/application/update") =>
      userApplicationResultController.update
    case POST(p"/application/delete") =>
      userApplicationResultController.deleteUserApplicationResult

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
      userDocumentsController.getAllUserDocuments
    case GET(p"/documents/get/$userDocumentsId") =>
      userDocumentsController.getUserDocumentsById(userDocumentsId)
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
    case GET(p"/password/all") =>
      userPasswordController.getAllUserPassword
    case GET(p"/password/get/$userPasswordId") =>
      userPasswordController.getUserPasswordById(userPasswordId)
    case POST(p"/password/create") =>
      userPasswordController.create
    case POST(p"/password/update") =>
      userPasswordController.update
    case POST(p"/password/delete") =>
      userPasswordController.deleteUserPassword

    //RELATIVE
    case GET(p"/relative/all") =>
      userRelativeController.getAllUserRelative
    case GET(p"/relative/get/$userRelativeId") =>
      userRelativeController.getUserRelativeById(userRelativeId)
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
    case GET(p"/subjects/all") =>
      userSubjectsController.getAllUserSubjects
    case GET(p"/subjects/get/$userSubjectId") =>
      userSubjectsController.getUserSubjectsById(userSubjectId)
    case POST(p"/subjects/create") =>
      userSubjectsController.create
    case POST(p"/subjects/update") =>
      userSubjectsController.update
    case POST(p"/subjects/delete") =>
      userSubjectsController.deleteUserSubjects

  }
}





