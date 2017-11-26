package cz.fi.muni.pa165.service.base;


import cz.fi.muni.pa165.exception.LibraryServiceException;
import cz.fi.muni.pa165.library.persistance.dao.base.CrudDao;
import cz.fi.muni.pa165.library.persistance.entity.base.EntityBase;

import java.util.List;

/**
 * Service implementation for basic create, update, delete, retrieve operations.
 *
 * @author Michael Cada
 */
public class CrudServiceImpl<TEntity extends EntityBase> implements CrudService<TEntity> {

    private CrudDao<TEntity> crudDao;

    public CrudServiceImpl(CrudDao<TEntity> crudDao) {
        if (crudDao == null) {
            throw new IllegalArgumentException("Dao is null.");
        }

        this.crudDao = crudDao;
    }

    @Override
    public TEntity findById(Long id) {
        try {
            return crudDao.findById(id);
        } catch(RuntimeException exception) {
            throw new LibraryServiceException("Exception while finding entity by id", exception);
        }
    }

    @Override
    public List<TEntity> findAll() {
        try {
            return crudDao.findAll();
        } catch (RuntimeException exception) {
            throw new LibraryServiceException("Exception while finding all entities", exception);
        }
    }

    @Override
    public Long create(TEntity entity) {
        try {
            crudDao.create(entity);
            return entity.getId();
        } catch(RuntimeException exception) {
            throw new LibraryServiceException("Exception while creating entity", exception);
        }
    }

    @Override
    public void update(TEntity entity) {
        try {
            crudDao.update(entity);
        } catch(RuntimeException exception) {
            throw new LibraryServiceException("Exception while updating entity", exception);
        }
    }

    @Override
    public void delete(TEntity entity) {
        try {
            crudDao.delete(entity);
        } catch(RuntimeException exception) {
            throw new LibraryServiceException("Exception while deleting entity", exception);
        }
    }
}