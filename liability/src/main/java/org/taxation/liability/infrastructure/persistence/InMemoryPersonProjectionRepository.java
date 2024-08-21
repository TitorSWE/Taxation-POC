package org.taxation.liability.infrastructure.persistence;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryPersonProjectionRepository implements PersonProjectionRepository {
    private final Map<String, PersonProjection> store = new ConcurrentHashMap<>();

    @Override
    public void save(PersonProjection personProjection) {
        store.put(personProjection.getPersonId(), personProjection);
    }

    @Override
    public PersonProjection findById(String id) {
        return store.get(id);
    }

    @Override
    public List<PersonProjection> findAll() {
        return new ArrayList<>(store.values());
    }
}
