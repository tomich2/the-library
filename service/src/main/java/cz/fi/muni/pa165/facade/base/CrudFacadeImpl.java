package cz.fi.muni.pa165.facade.base;

import cz.fi.muni.pa165.library.persistance.entity.base.EntityBase;
import cz.fi.muni.pa165.service.base.CrudService;
import cz.fi.muni.pa165.config.MappingService;

import java.util.List;

/**
 * Facade implementation for basic create, update, delete, retrieve operations.
 *
 * @author Michael Cada
 */
public class CrudFacadeImpl<TEntityDTO, TEntity extends EntityBase> implements CrudFacade<TEntityDTO> {

    private CrudService<TEntity> crudService;
    private MappingService mappingService;
    private Class<TEntityDTO> entityDtoType; // this is needed because java doesn't support getting
    private Class<TEntity> entityType;       // runtime type from generic type

    public CrudFacadeImpl(CrudService<TEntity> crudService,
                          MappingService mappingService,
                          Class<TEntityDTO> entityDtoType,
                          Class<TEntity> entityType) {
        this.crudService = crudService;
        this.mappingService = mappingService;
        this.entityDtoType = entityDtoType;
        this.entityType = entityType;
    }

    @Override
    public TEntityDTO findById(Long id) {
        return mappingService.map(crudService.findById(id), entityDtoType);
    }

    @Override
    public List<TEntityDTO> findAll() {
        return mappingService.map(crudService.findAll(), entityDtoType);
    }

    @Override
    public Long create(TEntityDTO entityDto) {
        TEntity entity = mappingService.map(entityDto, entityType);
        return crudService.create(entity);
    }

    @Override
    public void update(TEntityDTO entityDto) {
        TEntity entity = mappingService.map(entityDto, entityType);
        crudService.update(entity);
    }

    @Override
    public void delete(TEntityDTO entityDto) {
        TEntity entity = mappingService.map(entityDto, entityType);
        crudService.delete(entity);
    }

    @Override
    public void delete(Long id) {
        TEntity entity = crudService.findById(id);
        if (entity == null) {
            throw new IllegalArgumentException("Entity with given id doesn't exist");
        }

        crudService.delete(entity);
    }
}