package domain.util.exeptions

case class TokenFailExcerption(message: String = "", cause: Throwable = null)
  extends Exception(message, cause)
