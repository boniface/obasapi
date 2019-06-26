package util.connections

object NetConnection {


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
