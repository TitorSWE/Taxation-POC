import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.taxation.liability.model.Liability;
import org.taxation.liability.model.PersonAggregate;
import org.taxation.liability.model.PersonState;
import org.taxation.liability.model.commands.DeclarePerson;
import org.taxation.liability.model.events.LiabilityCreated;
import org.taxation.liability.model.events.PersonDeclared;
import org.taxation.liability.model.valueObjects.SocialSecurityNumber;
import org.taxation.liability.model.valueObjects.Year;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonAggregateTest {

    private FixtureConfiguration<PersonAggregate> fixture;

    @Before
    public void setUp(){
        this.fixture = new AggregateTestFixture<>(PersonAggregate.class);
    }

    @Test
    public void testExpectedEventsPersonDeclaration(){

        Year year = new Year(2024);
        SocialSecurityNumber socialSecurityNumber = new SocialSecurityNumber("7561234567897");
        String personId = UUID.randomUUID().toString();
        String liabilityId = UUID.randomUUID().toString();
        DeclarePerson command = new DeclarePerson(personId, liabilityId, year, socialSecurityNumber);

        fixture.givenNoPriorActivity()
                .when(command)
                .expectEvents(
                        new PersonDeclared(personId, year, socialSecurityNumber),
                        new LiabilityCreated(liabilityId, personId, year)
                );
    }

    @Test
    public void testExpectedStatePersonDeclaration(){

        Year year = new Year(2024);
        SocialSecurityNumber socialSecurityNumber = new SocialSecurityNumber("7561234567897");
        String personId = UUID.randomUUID().toString();
        String liabilityId = UUID.randomUUID().toString();
        DeclarePerson command = new DeclarePerson(personId, liabilityId, year, socialSecurityNumber);

        // Expected State
        PersonState expectedState = new PersonState(null, null, new Liability("", "", null, null))
                .createPerson(socialSecurityNumber, year)
                .createLiability(liabilityId, personId, year);

        fixture.givenNoPriorActivity()
                .when(command)
                .expectState(personAggregate -> {
                    PersonState actualState = personAggregate.getState();
                    assertEquals(expectedState, actualState);
                });
    }
}
