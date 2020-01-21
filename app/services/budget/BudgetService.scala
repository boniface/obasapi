package services.budget

import domain.budget.{Amount, Awards, Budget}
import zio.stm.TRef
import zio.stm.STM._

import scala.concurrent.Future

trait BudgetService {

  def awardBusary(awards: Awards):Future[Boolean]
  def addBudget(budget:Budget):Future[Boolean]
  def totalAwards: Future[List[Awards]]
  def totalSumAwards:Future[BigDecimal]
  def totalBudget:Future[BigDecimal]
  def awardBursar(from: TRef[Budget], to: TRef[Awards], amount:Amount) =
    atomically {
      for{
        balance <- from.get
        _ <- check(balance.amount >=amount.amount)
        _ <-from.update(_.amount - amount.amount)
        _ <-to.update(_.amount+amount.amount)
      } yield()
    }
}

