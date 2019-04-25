package edu.se309.app.backend.rest.service;

import edu.se309.app.backend.rest.repository.BaseRepository;
import edu.se309.app.backend.rest.service.interfaces.BaseService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Base Service
 *
 * @param <T>  Entity type
 * @param <ID> Type of ID
 * @param <R>  Type of base Repository
 */
public class BaseServiceImplementation<T, ID extends Serializable, R extends BaseRepository<T, ID>>
        implements BaseService<T, ID> {

    private R r;

    /**
     * Constructor for this service
     *
     * @param r repository associated with this service
     */
    public BaseServiceImplementation(R r) {
        this.r = r;
    }

    /**
     * returns the number of items in the database
     *
     * @return number of items in the database
     */
    @Override
    @Transactional
    public long count() {
        return r.count();
    }

    /**
     * Delete by ID
     *
     * @param id id of item to be deleted
     */
    @Override
    @Transactional
    public void deleteById(ID id) {
        r.deleteById(id);
    }

    /**
     * Find all items in database table
     *
     * @return items in database table
     */
    @Override
    @Transactional
    public List<T> findAll() {
        return r.findAll();
    }

    /**
     * Find database item by id
     *
     * @param id of item to be found
     * @return item with id
     */
    @Override
    @Transactional
    @NotNull
    public T findById(ID id) {
        Optional<T> t = r.findById(id);
        return nullCheck(t, "Invalid request: no such id: " + id);
    }

    /**
     * Returns associated repository
     *
     * @return associated repository
     */
    public R getRepository() {
        return r;
    }

    /**
     * checks if an Optional object contains an object or not
     *
     * @param t            type of object
     * @param errorMessage error message to throw if not found
     * @return retrieved object
     */
    @NotNull
    public T nullCheck(Optional<T> t, String errorMessage) {
        if (!t.isPresent()) {
            throw new ServiceException(errorMessage);
        } else {
            return t.get();
        }
    }

    /**
     * saves an object to database
     *
     * @param entity entity to be saved
     */
    @Override
    @Transactional
    public void save(T entity) {
        r.save(entity);
    }
}
