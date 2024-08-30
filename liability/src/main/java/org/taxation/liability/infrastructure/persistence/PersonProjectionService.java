package org.taxation.liability.infrastructure.persistence;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taxation.liability.model.events.LiabilityCreated;
import org.taxation.liability.model.events.LiabilityTypeDefined;
import org.taxation.liability.model.events.PersonDeclared;
import org.taxation.tools.Persistence.IRepository;

import java.util.List;

@Service
public class PersonProjectionService {

    private final IRepository<ProjectedPerson> repository;

    @Autowired
    public PersonProjectionService(IRepository<ProjectedPerson> repository) {
        this.repository = repository;
    }

    @QueryHandler
    public List<ProjectedPerson> handle(FindAllPersonsQuery query){
        return repository.findAll();
    }

    @QueryHandler
    public ProjectedPerson handle(FindPersonQuery query){
        return repository.findById(query.getPersonId());
    }


    @EventHandler
    public void on(PersonDeclared event) {
        ProjectedPerson projection = new ProjectedPerson();
        projection.setId(event.getPersonId());
        projection.setArrivalYear(event.getArrivalYear());
        projection.setSocialSecurityNumber(event.getSocialSecurityNumber());
        repository.save(projection);
    }

    @EventHandler
    public void on(LiabilityCreated event) {
        String personId = event.getPersonId();
        ProjectedPerson projectedPerson = repository.findById(personId);
        ProjectedLiability projectedLiability = new ProjectedLiability();
        projectedLiability.setId(event.getLiabilityId());
        projectedLiability.setYear(event.getYear());
        projectedLiability.setPersonId(event.getPersonId());
        projectedPerson.setLiability(projectedLiability);
        repository.save(projectedPerson);

    }

    /*
    * Merging data in the repository with data from the event
    * TO DO : use final and more functional style instead of var§iables
    * */
    @EventHandler
    public void on(LiabilityTypeDefined event) {
        String personId = event.getPersonId();
        ProjectedPerson projectedPerson = repository.findById(personId);
        ProjectedLiability updatedLiability = projectedPerson.getLiability();
        updatedLiability.setType(event.getLiabilityType());
        projectedPerson.setLiability(updatedLiability);
        repository.save(projectedPerson);
    }
}
