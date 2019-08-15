package util.connections

import com.typesafe.config.{Config, ConfigFactory}
import okhttp3.{MediaType, OkHttpClient, Request, RequestBody}

object NetConnection {
  val config: Config = ConfigFactory.load()
  val masterUrl: String = config.getString("weedfs.masterUrl")
  val volumeUrl: String = config.getString("weedfs.volumeUrl")
  val cdnUrl: String = config.getString("weedfs.cdnUrl")
  val cdnKey: String = config.getString("weedfs.cdnKey")

  def getClient: OkHttpClient ={
    new OkHttpClient()
  }

  def buildRequest(url:String, jsondata:String) (implicit mediaType: MediaType): Request ={
   new Request.Builder()
      .url(url)
      .post(RequestBody.create(mediaType, jsondata))
      .build()
  }
}
