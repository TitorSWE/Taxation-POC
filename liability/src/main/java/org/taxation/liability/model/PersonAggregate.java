package org.taxation.liability.model;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.taxation.liability.model.commands.DeclarePerson;
import org.taxation.liability.model.commands.SetLiabilityType;
import org.taxation.liability.model.events.LiabilityCreated;
import org.taxation.liability.model.events.LiabilityTypeDefined;
import org.taxation.liability.model.events.PersonDeclared;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class PersonAggregate {

    @AggregateIdentifier
    private String personId;
    private PersonState state = new PersonState(null, null, new Liability("", "", null, null));

    protected PersonAggregate(){}

    @CommandHandler
    public PersonAggregate(DeclarePerson command){
        apply(new PersonDeclared(command.getPersonId(), command.getArrivalYear(), command.getSocialSecurityNumber()));
        apply(new LiabilityCreated(command.getLiabilityId(), command.getPersonId(), command.getArrivalYear()));
    }

    @CommandHandler
    public void handle(SetLiabilityType command){
        apply(new LiabilityTypeDefined(command.getPersonId(), this.getState().getLiability().getLiabilityId(), command.getType()));
    }

    @EventSourcingHandler
    public void on(PersonDeclared event) {
        personId = event.getPersonId();
        state = state.createPerson(event.getSocialSecurityNumber(), event.getArrivalYear());
    }

    @EventSourcingHandler
    public void on(LiabilityCreated event) {
        state = state.createLiability(event.getLiabilityId(), event.getPersonId(), event.getYear());
    }

    @EventSourcingHandler
    public void on(LiabilityTypeDefined event){
        state = state.defineLiabilityType(event.getPersonId(), event.getType());
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public PersonState getState(){
        return this.state;
    }


}
