package edu.se309.app.backend.service.interfaces;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T, ID extends Serializable> {
	
	T findById(ID id);
	List<T> findAll();
	void save(T entity);
	long count();
	void deleteById(ID id);

}
