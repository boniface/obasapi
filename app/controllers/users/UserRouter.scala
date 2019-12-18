package controllers.users

import controllers.application.ApplicationStatusController
import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class UserRouter @Inject()(
                            userAddressController: UserAddressController, userApplicationController: UserApplicationController,
                            userCommunicationController: UserCommunicationController, userContactsController: UserContactsController,
                            userController: UserController, userDemographicsController: UserDemographicsController,
                            userDocumentsController: UserDocumentController, userPasswordController: UserPasswordController,
                            userRelativeController: UserRelativeController, userResultsController: UserResultsController,
                            userRoleController: UserRoleController, userTownController: UserTownController,
                            userMatricInstitutionController: UserMatricInstitutionController, userApplicationCourseController: UserApplicationCourseController,
                            userApplicationInstitutionController: UserApplicationInstitutionController, userMatricSubjectController: UserMatricSubjectController,
                            userTertiaryCourseController: UserTertiaryCourseController, userTertiaryInstitutionController: UserTertiaryInstitutionController,
                            userTertiarySubjectController: UserTertiarySubjectController
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

    // USER_TOWN
    case POST(p"/town/create") =>
      userTownController.create
    case GET(p"/town/get/$userId") =>
      userTownController.read(userId)
    case POST(p"/town/update") =>
      userTownController.update
    case GET(p"/town/delete") =>
      userTownController.delete

    // APPLICATION_COURSE
    case GET(p"/application/course/all") =>
      userApplicationCourseController.getAll
    case GET(p"/application/course/allforuser/$userId") =>
      userApplicationCourseController.getEntitiesForUser(userId)
    case GET(p"/application/course/getforapplication/$userId/$applicationId") =>
      userApplicationCourseController.read(userId, applicationId)
    case POST(p"/application/course/create") =>
      userApplicationCourseController.create
    case POST(p"/application/course/delete") =>
      userApplicationCourseController.delete

    // APPLICATION_INSTITUTION
    case GET(p"/application/institution/all") =>
      userApplicationInstitutionController.getAll
    case GET(p"/application/institution/allforuser/$userId") =>
      userApplicationInstitutionController.getEntitiesForUser(userId)
    case GET(p"/application/institution/getforapplication/$userId/$applicationId") =>
      userApplicationInstitutionController.read(userId, applicationId)
    case POST(p"/application/institution/create") =>
      userApplicationInstitutionController.create
    case POST(p"/application/institution/delete") =>
      userApplicationInstitutionController.delete

    //INSTITUTION_MATRIC
    case GET(p"/institution/matric/all") =>
      userMatricInstitutionController.getAll
    case GET(p"/institution/matric/get/$userId") =>
      userMatricInstitutionController.read(userId)
    case POST(p"/institution/matric/create") =>
      userMatricInstitutionController.create
    case POST(p"/institution/matric/update") =>
      userMatricInstitutionController.update
    case POST(p"/institution/matric/delete") =>
      userMatricInstitutionController.delete

    // INSTITUTION_MATRIC_SUBJECTS
    case GET(p"/institution/matric/subject/all") =>
      userMatricSubjectController.getAll
    case GET(p"/institution/matric/subject/allforuser/$userId") =>
      userMatricSubjectController.getEntitiesForUser(userId)
    case GET(p"/institution/matric/subject/get/$userId/$subjectId") =>
      userMatricSubjectController.read(userId, subjectId)
    case POST(p"/institution/matric/subject/create") =>
      userMatricSubjectController.create
    case POST(p"/institution/matric/subject/delete") =>
      userMatricSubjectController.delete

    // INSTITUTION_TERTIARY_COURSE
    case GET(p"/institution/tertiary/course/all") =>
      userTertiaryCourseController.getAll
    case GET(p"/institution/tertiary/course/allforuser/$userId") =>
      userTertiaryCourseController.getEntitiesForUser(userId)
    case GET(p"/institution/tertiary/course/getforapplication/$userId/$applicationId") =>
      userTertiaryCourseController.read(userId, applicationId)
    case POST(p"/institution/tertiary/course/create") =>
      userTertiaryCourseController.create
    case POST(p"/institution/tertiary/course/delete") =>
      userTertiaryCourseController.delete

    // INSTITUTION_TERTIARY
    case GET(p"/institution/tertiary/all") =>
      userTertiaryInstitutionController.getAll
    case GET(p"/institution/tertiary/allforuser/$userId") =>
      userTertiaryInstitutionController.getEntitiesForUser(userId)
    case GET(p"/institution/tertiary/getforapplication/$userId/$applicationId") =>
      userTertiaryInstitutionController.read(userId, applicationId)
    case POST(p"/institution/tertiary/create") =>
      userTertiaryInstitutionController.create
    case POST(p"/institution/tertiary/delete") =>
      userTertiaryInstitutionController.delete

    // INSTITUTION_TERTIARY_SUBJECT
    case GET(p"/institution/tertiary/subject/all") =>
      userTertiarySubjectController.getAll
    case GET(p"/institution/tertiary/subject/allforuser/$userId") =>
      userTertiarySubjectController.getEntitiesForUser(userId)
    case GET(p"/institution/tertiary/subject/allforapplication/$userId/$applicationId") =>
      userTertiarySubjectController.getEntitiesForApplication(userId, applicationId)
    case GET(p"/institution/tertiary/subject/getforapplication/$userId/$applicationId/$subjectId") =>
      userTertiarySubjectController.read(userId, applicationId, subjectId)
    case GET(p"/institution/tertiary/subject/deleteforapplication/$userId/$applicationId") =>
      userTertiarySubjectController.deleteEntitiesForApplication(userId, applicationId)
    case POST(p"/institution/tertiary/subject/create") =>
      userTertiarySubjectController.create
    case POST(p"/institution/tertiary/subject/delete") =>
      userTertiarySubjectController.delete

  }
}





