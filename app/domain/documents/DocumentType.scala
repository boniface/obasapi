package domain.documents

import play.api.libs.json.Json

case class DocumentType(
                   documentTypeId:String,
                   documentTypename:String
                  )
object DocumentType{
  implicit val documentTypeFmt =Json.format[DocumentType]

}
