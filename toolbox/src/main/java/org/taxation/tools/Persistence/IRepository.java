<<<<<<< HEAD
package org.taxation.tools.Persistence;

import java.util.List;

public interface IRepository<T extends ProjectedEntity> {
    void save(T projectedEntity);
    T findById(String id);
    List<T> findAll();
=======
package org.taxation.tools.Persistence;public interface IRepository {
>>>>>>> 34f407bdf061ef716a6c180731a0f8926eb95942
}
