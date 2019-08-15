package controllers.storage

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class UploadRouter@Inject()
(fileManagerController: FileManagerController) extends SimpleRouter {
  override def routes: Routes = {
    case POST(p"/upload") =>
      fileManagerController.upload
    case GET(p"/delete/$vol/$fid/$filename") =>
      fileManagerController.deleteFile(vol,fid,filename)
    case GET(p"/$vol/$fid/$filename")=>
      fileManagerController.getFile(vol,fid,filename)
    case  GET( p"/resize/$vol/$fid/$filename"?q"height=$height" & q"width=$width" ) =>
      fileManagerController.getFileSize(vol,fid,filename,height,width)
  }
}