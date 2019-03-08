package edu.se309.app.backend.service.interfaces;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T, ID extends Serializable> {
	long count();

	void deleteById(ID id);

	List<T> findAll();

	T findById(ID id);

	void save(T entity);

}
