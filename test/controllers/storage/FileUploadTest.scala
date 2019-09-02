package controllers.storage

import java.io.{File, FileInputStream}

import okhttp3._
import org.scalatest.FunSuite


class FileUploadTest extends FunSuite {
//  val url = getClass().getResource("3.JPG")
  val file = new File("/home/dit-lab/IdeaProjects/obasapi/test/controllers/storage/3.JPG")
  println(file.getAbsolutePath)
  val logo = new FileInputStream(file)
  val baseUrl = "http://localhost:9000"
  val UPLOAD = "upload"
  val SITEID = "SITEID"

  test("Test File Upload ") {
    val MEDIA_TYPE = MediaType.parse("image/png")
    val requestBody = new MultipartBody
      .Builder()
      .setType(MultipartBody.FORM)
      .addFormDataPart(UPLOAD, file.getName, RequestBody.create(MEDIA_TYPE, file))
      .addFormDataPart(SITEID, "file name")
      .build()
    val request = new Request.Builder()
      .header("Authorization", "HASHCODE@KEYS@")
      .url(baseUrl + "/file/upload")
      .post(requestBody)
      .build()

    val content = new OkHttpClient().newCall(request).execute().body().string()

    println(" The results is ", content)
    
  }


}
