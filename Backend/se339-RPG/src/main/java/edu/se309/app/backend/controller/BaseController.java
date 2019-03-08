package edu.se309.app.backend.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import edu.se309.app.backend.service.interfaces.BaseService;

public class BaseController<T, ID extends Serializable, R extends BaseService<T, ID>> {

	private R r;

	public BaseController(R r) {
		this.r = r;

	}

	@GetMapping("/count")
	public long count() {
		return r.count();
	}

	@DeleteMapping("/{id}")
	String deleteById(ID id) {
		r.deleteById(id);
		String className = this.getClass().getSimpleName();
		className = className.substring(0, className.lastIndexOf('C'));
		return "Deleted " + className + " with id: " + id.toString();
	}

	@GetMapping
	public List<T> findAll() {
		return r.findAll();
	}

	@GetMapping("/{id}")
	public T findById(ID id) {
		return r.findById(id);
	}

	public R getService() {
		return r;
	}

}
