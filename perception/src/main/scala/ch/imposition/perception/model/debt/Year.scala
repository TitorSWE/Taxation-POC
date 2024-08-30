package ch.imposition.perception.model.debt

case class Year(year: Int)

object Year {
  def empty(): Year = Year(0)
}
