package edu.se309.app.backend.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Base Repository used to control which repository functions are exposed.
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {


    @Override
    long count();

    @Override
    void deleteById(ID id);

    /**
     * Return list of all
     *
     * @return list of all
     */
    @Override
    List<T> findAll();

    /**
     * Find by id
     *
     * @param id id of requested
     * @return item with id
     */
    @Override
    Optional<T> findById(ID id);

    /**
     * Save to database
     *
     * @param entity entity to be saved
     * @param <S>    type of entity to be saved
     * @return object that was saved
     */
    @Override
    <S extends T> S save(S entity);
}
