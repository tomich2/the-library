package cz.fi.muni.pa165.library.persistance.dao.base;

import java.util.List;

/**
 * Interface for basic create, retrieve, update, delete operations.
 *
 * @author Michael Cada
 */
public interface CrudDao<TEntity> {

    /**
     * Finds all entities
     *
     * @return all entities
     */
    List<TEntity> findAll();

    /**
     * Finds entity by id
     *
     * @param id Id of entity
     * @return found entity
     */
    TEntity findById(Long id);

    /**
     * Creates new entity
     *
     * @param entity to create
     */
    void create(TEntity entity);

    /**
     * Deletes entity
     *
     * @param entity
     */
    void delete(TEntity entity);

    /**
     * Updates entity
     *
     * @param entity
     */
    void update(TEntity entity);
}
