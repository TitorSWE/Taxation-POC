package org.taxation.liability.infrastructure.persistence;

import java.util.List;

public interface PersonProjectionRepository {
    void save(PersonProjection personProjection);
    PersonProjection findById(String id);
    List<PersonProjection> findAll();
}
