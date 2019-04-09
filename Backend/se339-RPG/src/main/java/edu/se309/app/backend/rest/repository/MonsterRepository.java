package edu.se309.app.backend.rest.repository;

import edu.se309.app.backend.rest.entity.Monster;

import java.util.Optional;

public interface MonsterRepository extends BaseRepository<Monster, Integer> {

    @Override
    void deleteAll();

    Optional<Monster> findFirstByOrderByIdAsc();
}
