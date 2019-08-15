package services.setup.db.impl.cockroachdb

import domain.users.{UserApplicationResult, UserContacts, UserDemographics, UserInstitution, UserPassword, UserRelative, UserSubjects}
import services.address.{AddressTypeService, ContactTypeService}
import services.application.{ApplicantTypeService, ApplicationResultService, ApplicationStatusService}
import services.demographics.{GenderService, RaceService, RoleService, TitleService}
import services.documents.{DocumentService, DocumentTypeService}
import services.institutions.{SchoolService, UniversityService}
import services.location.{LocationService, LocationTypeService}
import services.mail.{MailApiService, MailConfigService, SmtpConfigService}
import services.setup.db.DBSetupService
import services.subjects.{MatricSubjectsService, UniversityCoursesService}
import services.users.{UserAddressService, UserApplicationResultService, UserCommunicationService, UserContactsService, UserDemographicsService, UserDocumentsService, UserInstitutionService, UserPasswordService, UserRelativeService, UserResultsService, UserRoleService, UserService, UserSubjectsService}
import services.login.{LoginService,LoginTokenService}


import scala.concurrent.Future

 class DBSetupServiceImpl extends DBSetupService {

  def createLocationTables(): Future[Boolean] = {
    LocationService.roach.createTable
    LocationTypeService.roach.createTable
  }

  def createAddressTables(): Future[Boolean] = {
    AddressTypeService.roach.createTable
    ContactTypeService.roach.createTable
  }

  def createApplicationTables(): Future[Boolean] = {
    ApplicantTypeService.roach.createTable
    ApplicationResultService.roach.createTable
    ApplicationStatusService.roach.createTable
  }

  def createDemographicsTables(): Future[Boolean] = {
    GenderService.roach.createTable
    RaceService.roach.createTable
    RoleService.roach.createTable
    TitleService.roach.createTable
  }

  def createDocumentTables(): Future[Boolean] = {
    DocumentService.roach.createTable
    DocumentTypeService.roach.createTable
  }

  def createInstitutionTables(): Future[Boolean] = {
    SchoolService.roach.createTable
    UniversityService.roach.createTable
  }

  def createMailTables(): Future[Boolean] = {
    MailApiService.roach.createTable
    MailConfigService.roach.createTable
    SmtpConfigService.roach.createTable
  }

  def createSubjectTables(): Future[Boolean] = {
    MatricSubjectsService.roach.createTable
    UniversityCoursesService.roach.createTable
  }

  def createUserTables(): Future[Boolean] = {
    UserAddressService.apply.createTable
    UserApplicationResultService.roach.createTable
    UserCommunicationService.roach.createTable
    UserContactsService.roach.createTable
    UserDemographicsService.roach.createTable
    UserDocumentsService.roach.createTable
    UserInstitutionService.roach.createTable
    UserPasswordService.apply.createTable
    UserRelativeService.roach.createTable
    UserRoleService.roach.createTable
    UserSubjectsService.roach.createTable
    UserResultsService.roach.createTable
    UserService.apply.createTable


  }

  def createLoginTables(): Future[Boolean] = {

    LoginTokenService.apply.createTable


  }

  override def createTables: Future[Boolean] = {

    createAddressTables()

    createApplicationTables()

    createDemographicsTables()

    createDocumentTables()

    createInstitutionTables()

    createLocationTables()

    createMailTables()

    createSubjectTables()

    createUserTables()

    createLoginTables()

  }
}
