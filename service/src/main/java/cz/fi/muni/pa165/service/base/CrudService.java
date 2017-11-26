package cz.fi.muni.pa165.service.base;

import cz.fi.muni.pa165.library.persistance.entity.base.EntityBase;

import java.util.List;

/**
 * Service interface for basic create, update, delete, retrieve operations.
 *
 * @author Michael Cada
 */
public interface CrudService<TEntity extends EntityBase> {

    /**
     * Finds entity by id.
     *
     * @param id ID of entity.
     * @return Found Entity.
     */
    TEntity findById(Long id);

    /**
     * Finds all entities.
     *
     * @return List of all entities.
     */
    List<TEntity> findAll();

    /**
     * Saves new entity.
     *
     * @param entity Entity to create.
     * @return id of created entity
     */
    Long create(TEntity entity);

    /**
     * Updates entity.
     *
     * @param entity Entity to update.
     */
    void update(TEntity entity);

    /**
     * Deletes entity.
     *
     * @param entity Entity to delete.
     */
    void delete(TEntity entity);

}