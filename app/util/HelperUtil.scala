package util

import java.time.{LocalDateTime, ZoneId}
import java.util.{Date, Random}

import com.typesafe.config.ConfigFactory
import org.apache.commons.lang3.RandomStringUtils
import org.jsoup.Jsoup

import scala.util.{Failure, Success, Try}


object HelperUtil {

  import java.net._

  val config = ConfigFactory.load()
  val browserTimeOut = config.getInt("browser.timeout")


  def md5Hash(text: String): String = {
    val hash = text + InetAddress.getLocalHost.getHostName
    java.security.MessageDigest.getInstance("MD5").digest(hash.getBytes()).map(0xFF & _).map {
      "%02x".format(_)
    }.foldLeft("") {
      _ + _
    }
  }

  def cleanMd5Hash(text: String): String = {
    java.security.MessageDigest.getInstance("MD5").digest(text.getBytes()).map(0xFF & _).map {
      "%02x".format(_)
    }.foldLeft("") {
      _ + _
    }
  }

  def getLocalDateTimeFromDate(date: Date): LocalDateTime = {
    date.toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDateTime()
  }

  private def generateOrganisationCode(name: String): String = {
    val count: Int = 4
    val useLetters: Boolean = true
    val useNumbers: Boolean = false
    val choseFrom = name.toCharArray
    RandomStringUtils.random(count, 0, 0, useLetters, useNumbers, choseFrom, new Random())
  }

  private def getSalt(): String = {
    val length: Int = 5
    val useLetters: Boolean = true
    val useNumbers: Boolean = true
    RandomStringUtils.random(length, useLetters, useNumbers)
  }

  def codeGen(name: String): String = {
    val code = generateOrganisationCode(name)
      .toCharArray
      .sortWith(_ < _)
      .mkString("").toUpperCase

    val salt = getSalt()
      .toCharArray
      .sortWith(_ < _)
      .mkString("").toUpperCase
    (code + "-" + salt)
  }


  def getBrowser(url: String): String = {
    Jsoup.connect(url)
      .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.99 Safari/537.36")
      .timeout(browserTimeOut)
      .referrer("https://www.google.com/")
      .post().outerHtml()
  }

  def getIntFromString(value: String): Int = {
    Try(Integer.parseInt(value)) match {
      case Success(ans) => ans
      case Failure(ex) => 0
    }
  }

  def getSiteURL: String = {
    config.getString("base.url")
  }

}
