package dao;

import domain.BaseObject;

/**
 * Common interface for all DAOs
 *
 */
public interface GenericDao<T extends BaseObject> {

    T save(T value);

    T findOne(Long id);

    void remove(T toRemove);

    /*
        Transaction handling, very crude, there are better ways to do this.
     */
    
    void refresh(Object o);

    void startTransaction();

    void commitTransaction();

    void rollbackTransaction();
}
