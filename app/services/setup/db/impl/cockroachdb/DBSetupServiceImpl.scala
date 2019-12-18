package services.setup.db.impl.cockroachdb

import services.academics.{CourseService, CourseSubjectService, SubjectService}
import services.address.{AddressTypeService, ContactTypeService}
import services.application.{ApplicantTypeService, ApplicationService, ApplicationStatusService, ApplicationTypeService}
import services.demographics.{DistrictService, DistrictTownService, GenderService, ProvinceDistrictService, ProvinceService, RaceService, RoleService, TitleService, TownService}
import services.documents.{DocumentService, DocumentStatusService, DocumentTypeService}
import services.institutions.{InstitutionAddressService, InstitutionContactService, InstitutionCourseService, InstitutionLocationService, InstitutionService, InstitutionTypeService}
import services.location.{LocationService, LocationTypeService}
import services.log.LogEventService
import services.login.LoginTokenService
import services.mail.{EmailMessageService, MailApiService, MailConfigService, SmtpConfigService}
import services.security.{ApiKeysService, ResetTokenService}
import services.setup.db.DBSetupService
import services.users._
import services.util.generic.GenericStatusService

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
    ApplicationService.apply.createTable
    ApplicationTypeService.apply.createTable
    ApplicationStatusService.roach.createTable
  }

  def createDemographicsTables(): Future[Boolean] = {
    GenderService.roach.createTable
    RaceService.roach.createTable
    RoleService.roach.createTable
    TitleService.roach.createTable
    DistrictService.roach.createTable
    ProvinceService.roach.createTable
    TownService.roach.createTable
    ProvinceDistrictService.apply.createTable
    DistrictTownService.apply.createTable
  }

  def createDocumentTables(): Future[Boolean] = {
    DocumentService.roach.createTable
    DocumentTypeService.roach.createTable
    DocumentStatusService.apply.createTable
  }

  def createInstitutionTables(): Future[Boolean] = {
    InstitutionTypeService.apply.createTable
    InstitutionService.apply.createTable
    InstitutionAddressService.apply.createTable
    InstitutionContactService.apply.createTable
    InstitutionLocationService.apply.createTable
    InstitutionCourseService.apply.createTable
  }

  def createMailTables(): Future[Boolean] = {
    MailApiService.roach.createTable
    MailConfigService.roach.createTable
    SmtpConfigService.roach.createTable
    EmailMessageService.roach.createTable
  }

  def createAcademicsTables(): Future[Boolean] = {
    CourseService.apply.createTable
    SubjectService.apply.createTable
    CourseSubjectService.apply.createTable
  }

  def createUserTables(): Future[Boolean] = {
    UserAddressService.apply.createTable
    UserApplicationService.roach.createTable
    UserCommunicationService.roach.createTable
    UserContactsService.roach.createTable
    UserDemographicsService.roach.createTable
    UserDocumentService.roach.createTable
    UserPasswordService.apply.createTable
    UserRelativeService.roach.createTable
    UserRoleService.roach.createTable
    UserResultsService.roach.createTable
    UserService.apply.createTable
    UserChangePasswordService.apply.createTable
    UserTownService.apply.createTable
    UserApplicationCourseService.apply.createTable
    UserApplicationInstitutionService.apply.createTable
    UserMatricInstitutionService.apply.createTable
    UserMatricSubjectService.apply.createTable
    UserTertiaryCourseService.apply.createTable
    UserTertiaryInstitutionService.apply.createTable
    UserTertiarySubjectService.apply.createTable
  }

  def createLoginTables(): Future[Boolean] ={
    LoginTokenService.apply.createTable
  }

  def createSecurityTables(): Future[Boolean] = {
    ApiKeysService.apply.createTable
    ResetTokenService.apply.createTable

  }

  def createLogTables(): Future[Boolean] ={
    LogEventService.apply.createTable
  }

  def createGenericUtilTables(): Future[Boolean] = {
    GenericStatusService.roach.createTable
  }

  override def createTables: Future[Boolean] = {

    createAddressTables()

    createApplicationTables()

    createDemographicsTables()

    createDocumentTables()

    createInstitutionTables()

    createLocationTables()

    createMailTables()

    createAcademicsTables()

    createUserTables()

    createSecurityTables()

    createLogTables()

    createLoginTables()

    createGenericUtilTables()

  }
}
