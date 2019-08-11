package controllers.storage

import java.io.File

import io.storage.FileManagerRepository
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class UploadTest extends FunSuite {
  test("testGetAllZones") {
    val url = getClass().getResource("3.JPG")
    val file = new File(url.getPath())

    val result = Await.result(FileManagerRepository.apply.postFile(file), 2.minutes)

    println("The results ", result)

  }

}


