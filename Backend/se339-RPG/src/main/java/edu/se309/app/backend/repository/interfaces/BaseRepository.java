package edu.se309.app.backend.repository.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Base Repository used to control which repository functions are exposed.
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
	@Override
	long count();

	@Override
	void deleteById(ID id);

	@Override
	List<T> findAll();

	@Override
	Optional<T> findById(ID id);

	@Override
	<S extends T> S save(S entity);

}
