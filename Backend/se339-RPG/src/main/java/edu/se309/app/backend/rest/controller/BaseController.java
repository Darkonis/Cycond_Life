package edu.se309.app.backend.rest.controller;

import edu.se309.app.backend.rest.service.interfaces.BaseService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;
import java.util.List;

/**
 * Base Controller
 *
 * @param <T>  Type for given entity to be controlled
 * @param <ID> Type for the id
 * @param <S>  Type for the baseService
 */
public class BaseController<T, ID extends Serializable, S extends BaseService<T, ID>> {

    private S s;

    /**
     * Constructor for this Controller
     *
     * @param s Service associated with this controller
     */
    public BaseController(S s) {
        this.s = s;
    }

    /**
     * Returns the number of items in database table
     *
     * @return number of items in database table
     */
    @GetMapping("/count")
    public long count() {
        return s.count();
    }

    /**
     * Delete item with id
     *
     * @param id id of item to be deleted
     * @return String confirming item was deleted by id
     */
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") ID id) {
        s.deleteById(id);
        String className = this.getClass().getSimpleName();
        className = className.substring(0, className.lastIndexOf('C'));
        return "Deleted " + className + " with id: " + id.toString();
    }

    /**
     * returns a list of all items in database table
     *
     * @return list of all items in database table
     */
    @GetMapping("/")
    public List<T> findAll() {
        return s.findAll();
    }

    /**
     * Get item from table by ID
     *
     * @param id ID of item
     * @return Item with ID
     */
    @GetMapping("/{id}")
    public T findById(@PathVariable ID id) {
        return s.findById(id);
    }

    /**
     * Returns the service associated with this controller
     *
     * @return service associated with this controller
     */
    public S getService() {
        return s;
    }
}
