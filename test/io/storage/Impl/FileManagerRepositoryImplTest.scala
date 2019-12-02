package io.storage.Impl

import java.io.File

import io.storage.FileManagerRepository
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class FileManagerRepositoryImplTest extends FunSuite {

  test("testDeleteFile") {
    val uri = "/a81353c3"
    val result = Await.result(FileManagerRepository.apply.deleteFile(uri), 2.minutes)
    println("Get result", result)
  }

  test("testPostFile") {
    val file = new File("/home/dit-lab/IdeaProjects/obasapi/test/controllers/storage/3.JPG")

    val result = Await.result(FileManagerRepository.apply.postFile(file), 2.minutes)

    println("The results ", result)
  }

  test("testGetFile") {
    val uri = "/a81353c3"
    val result = Await.result(FileManagerRepository.apply.getFile(uri), 2.minutes)
    println("Get result", result)

  }

}
