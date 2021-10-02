package cz.cvut.fit.bietjv.exchange.business;

import cz.cvut.fit.bietjv.exchange.persistence.dtos.IEntityDto;
import cz.cvut.fit.bietjv.exchange.persistence.entities.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudService<T extends AbstractEntity,
        TDto extends IEntityDto<T>,
        TRepository extends CrudRepository<T, Integer>> {

    @Autowired
    protected TRepository repository;

    public List<T> index() {
        List<T> allRecords = new ArrayList<>();
        repository.findAll().forEach(allRecords::add);
        return allRecords;
    }

    public T add(TDto dto) {
        T record = dto.map();
        repository.save(record);
        return record;
    }

    public T update(int id, TDto dto) {
        Optional<T> record = repository.findById(id);
        if (record.isPresent()) {
            T newRecord = dto.map();
            newRecord.setId(id);
            repository.save(newRecord);
            return newRecord;
        }
        return null;
    }

    public boolean destroy(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
