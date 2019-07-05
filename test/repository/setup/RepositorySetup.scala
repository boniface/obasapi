package repository.setup

import org.scalatest.FunSuite
import repository.mail.MailConfigRepository

import scala.concurrent.Await
import scala.concurrent.duration._

class RepositorySetup extends FunSuite{
  test("createDB"){
    val result = Await.result(MailConfigRepository.roach.createTable, 2 minutes)
    println(" The Result is ", result)
    assert(result)
  }

}
