package ch.imposition.perception.model.debt

import akka.serialization.jackson.JsonSerializable

case class DebtState(liabilityID: String, personId: String, year: Year, amount: Double) extends JsonSerializable {

  def createDebt(liabilityId: String, personId: String, year: Year): DebtState =
    DebtState(liabilityID = liabilityId, personId = personId, year = year, amount = this.amount)

}

object DebtState {
  def empty(): DebtState = DebtState("", "", Year.empty(), 0)

}