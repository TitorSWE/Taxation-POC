package org.taxation.liability.infrastructure.persistence;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taxation.liability.model.Liability;
import org.taxation.liability.model.events.LiabilityCreated;
import org.taxation.liability.model.events.LiabilityTypeDefined;
import org.taxation.liability.model.events.PersonDeclared;

import java.util.List;

@Service
public class PersonProjectionService {

    private final PersonProjectionRepository repository;

    @Autowired
    public PersonProjectionService(PersonProjectionRepository repository) {
        this.repository = repository;
    }

    @QueryHandler
    public List<PersonProjection> handle(FindAllPersonsQuery query){
        return repository.findAll();
    }

    @QueryHandler
    public PersonProjection handle(FindPersonQuery query){
        return repository.findById(query.getPersonId());
    }


    @EventHandler
    public void on(PersonDeclared event) {
        PersonProjection projection = new PersonProjection();
        projection.setPersonId(event.getPersonId());
        projection.setArrivalYear(event.getArrivalYear());
        projection.setSocialSecurityNumber(event.getSocialSecurityNumber());
        repository.save(projection);
    }

    @EventHandler
    public void on(LiabilityCreated event) {
        String personId = event.getPersonId();
        PersonProjection personProjection = repository.findById(personId);
        LiabilityProjection liabilityProjection = new LiabilityProjection();
        liabilityProjection.setLiabilityId(event.getLiabilityId());
        liabilityProjection.setYear(event.getYear());
        liabilityProjection.setPersonId(event.getPersonId());
        personProjection.setLiability(liabilityProjection);
        repository.save(personProjection);

    }

    /*
    * Merging data in the repository with data from the event
    * TO DO : use final and more functional style instead of var§iables
    * */
    @EventHandler
    public void on(LiabilityTypeDefined event) {
        String personId = event.getPersonId();
        PersonProjection personProjection = repository.findById(personId);
        LiabilityProjection updatedLiability = personProjection.getLiability();
        updatedLiability.setType(event.getType());
        personProjection.setLiability(updatedLiability);
        repository.save(personProjection);
    }
}
