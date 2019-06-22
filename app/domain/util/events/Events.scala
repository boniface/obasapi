package domain.util.events

import javax.inject.Singleton

@Singleton
object Events {
  def VALIDATED = "VALIDATED"
  def LOGGEDIN = "LOGGEDIN"
  def LOGGEOUT = "LOGGEDOUT"
  def TOKENCREATED = "TOKEN_CREATED"
  def TOKENFAILED = "TOKEN_FAILED"
  def TOKENSUCCESSMESSAGE="Token Successfully Created "
  def TOKENFAILMESSAGE="Token  Creation Failed "
  def TOKENVALID = "TOKEN_VALID"
  def TOKENINVALID = "TOKEN_INVALID"
  def RESPONSE="RESPONSE"
  def JASONPARSE = "JASON PARSE"
  def BROWSER_AGENT="User-Agent"
}
