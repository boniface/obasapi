package repository.storage.impl.cockroachdb

import domain.storage.FileData
//import repository.storage.FileDataRepository

import scala.concurrent.Future

//class FileDataRepositoryImpl extends FileDataRepository {
//
////  override def saveEntity(entity: FileData): Future[Option[FileData]] = {
////    FileDataTable.saveEntity(entity)
////  }
////
////  override def getEntities: Future[Seq[FileData]] = {
////    FileDataTable.getEntities
////  }
////
////  override def getEntity(id: String): Future[Option[FileData]] = {
////    FileDataTable.getEntity(id)
////  }
////
////  override def deleteEntity(entity: FileData): Future[Boolean] = {
////    FileDataTable.deleteEntity(entity.id)map(value=> value.isValidInt)
////  }
////
////  override def createTable: Future[Boolean] = {
////    Future.successful(FileDataTable.createTable)
////  }
//}
