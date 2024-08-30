<<<<<<< HEAD
package org.taxation.tools.Persistence;

import org.springframework.stereotype.Component;

@Component
public class InMemoryRepositoryFactory<T extends ProjectedEntity> implements IRepositoryFactory<T> {

    @Override
    public IRepository<T> createRepository() {
        return new InMemoryRepository<T>();
    }
}
=======
package org.taxation.tools.Persistence;public class InMemoryRepositoryFactory {
}
>>>>>>> 34f407bdf061ef716a6c180731a0f8926eb95942
