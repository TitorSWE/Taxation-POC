package org.taxation.liability.infrastructure.resources;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taxation.liability.infrastructure.persistence.FindAllPersonsQuery;
import org.taxation.liability.infrastructure.persistence.FindPersonQuery;
import org.taxation.liability.infrastructure.persistence.PersonProjection;
import org.taxation.liability.infrastructure.resources.request.DeclarePersonRequest;
import org.taxation.liability.infrastructure.resources.request.SetLiabilityRequest;
import org.taxation.liability.infrastructure.resources.response.DeclarePersonResponse;
import org.taxation.liability.infrastructure.resources.response.ErrorResponse;
import org.taxation.liability.infrastructure.resources.response.Response;
import org.taxation.liability.infrastructure.resources.response.SetLiabilityResponse;
import org.taxation.liability.model.commands.DeclarePerson;
import org.taxation.liability.model.commands.SetLiabilityType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class LiabilityRestEndpoint {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public LiabilityRestEndpoint(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/declare")
    public CompletableFuture<ResponseEntity<Response>> declarePerson(@RequestBody DeclarePersonRequest declarePersonRequest) {

        String personId = UUID.randomUUID().toString();
        String liabilityId = UUID.randomUUID().toString();

        DeclarePerson command = new DeclarePerson(
                personId,
                liabilityId,
                declarePersonRequest.getYear(),
                declarePersonRequest.getSocialSecurityNumber()
        );

        return commandGateway.send(command)
                .thenApply(result -> {
                    // Create and return a success response
                    DeclarePersonResponse response = new DeclarePersonResponse(
                            "Person declared successfully",
                            personId,
                            liabilityId
                    );
                    return ResponseEntity.status(HttpStatus.CREATED).body( (Response) response);
                })
                .exceptionally(ex -> {
                    // Create and return an error response
                    ErrorResponse errorResponse = new ErrorResponse(
                            "Failed to declare person",
                            ex.getMessage()
                    );
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
                });
    }

    @PutMapping("/type/{personId}")
    public CompletableFuture<ResponseEntity<Response>> setPersonLiability(@PathVariable("personId") String personId, @RequestBody SetLiabilityRequest setLiabilityRequest){
        SetLiabilityType command = new SetLiabilityType();
        command.setPersonId(personId);
        command.setType(setLiabilityRequest.getType());

        return commandGateway.send(command)
                .thenApply(result -> {
                    SetLiabilityResponse response = new SetLiabilityResponse(
                            "Liability type defined",
                            personId,
                            setLiabilityRequest.getType()
                    );
                    return ResponseEntity.status(HttpStatus.OK).body((Response) response);
                })
                .exceptionally(ex -> {
                    // Create and return an error response
                    ErrorResponse errorResponse = new ErrorResponse(
                            "Failed to set liability",
                            ex.getMessage()
                    );
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
                });
    }

    @GetMapping("/persons")
    public CompletableFuture<List<PersonProjection>> findAllPersons(){
        return queryGateway.query(new FindAllPersonsQuery(), ResponseTypes.multipleInstancesOf(PersonProjection.class));
    }

    @GetMapping("/{personId}")
    public CompletableFuture<Optional<PersonProjection>> findPerson(@PathVariable("personId") String personId){
        return queryGateway.query(new FindPersonQuery(personId), ResponseTypes.optionalInstanceOf(PersonProjection.class));
    }
}