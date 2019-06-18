package domain.documents

import play.api.libs.json.Json

class DocumentType(
                   documentTypeId:String,
                   name:String
                  )
object DocumentType{
  implicit val DocumentTypeFmt =Json.format[DocumentType]

}
