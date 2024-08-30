package ch.imposition.perception.model.debt

import akka.actor.typed.Behavior
import akka.persistence.typed.PersistenceId
import akka.persistence.typed.scaladsl.{Effect, EventSourcedBehavior}
import akka.serialization.jackson.JsonSerializable

import java.util.UUID

object DebtEntity {

  sealed trait DebtCommand extends JsonSerializable
  case class CreateDebt(liabilityId: String, personId: String, year: Year) extends DebtCommand

  sealed trait DebtEvent extends JsonSerializable
  case class DebtCreated(liabilityId: String, personId: String, year: Year) extends DebtEvent

  def debtCommandHandler(state: DebtState, command: DebtCommand) : Effect[DebtEvent, DebtState] = {
    command match {
      case CreateDebt(liabilityId, personId, year) => Effect.persist(DebtCreated(liabilityId, personId, year))
      case _ => throw new NotImplementedError("TODO")
    }
  }

  def debtEventHandler(state: DebtState, event: DebtEvent): DebtState = {
    event match {
      case DebtCreated(liabilityId, personId, year) => state.createDebt(liabilityId, personId, year)
      case _ => throw new NotImplementedError("TODO")
    }
  }

  def apply(id: PersistenceId): Behavior[DebtCommand] =
    EventSourcedBehavior[DebtCommand, DebtEvent, DebtState] (
      persistenceId = id,
      emptyState = DebtState.empty(),
      commandHandler = (state, cmd) => throw new NotImplementedError("TODO: process the command & return an Effect"),
      eventHandler = (state, evt) => throw new NotImplementedError("TODO: process the event return the next state")
    )

}
