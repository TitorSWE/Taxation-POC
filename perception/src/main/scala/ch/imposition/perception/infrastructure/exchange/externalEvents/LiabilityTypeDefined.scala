package ch.imposition.perception.infrastructure.exchange.externalEvents

import ch.imposition.perception.model.debt.{LiabilityType, Year}

case class LiabilityTypeDefined(personId: String, liabilityId: String, liabilityType: LiabilityType, year: Year)
