package edu.se309.app.backend.repository.Interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Base Repository used to control which repository functions are exposed. 
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T,ID> {
	Optional<T> findById(ID id);
	List<T> findAll();
	<S extends T> S save(S entity);
	long count();
	void deleteById();

}
