package edu.se309.app.backend.rest.repository;

import edu.se309.app.backend.rest.entity.Monster;

import java.util.Optional;

/**
 * Monster Repository
 */
public interface MonsterRepository extends BaseRepository<Monster, Integer> {

    /**
     * Delete all
     */
    @Override
    void deleteAll();

    /**
     * Find first monster by ID
     * @return Monster with lowest ID
     */
    Optional<Monster> findFirstByOrderByIdAsc();
}
