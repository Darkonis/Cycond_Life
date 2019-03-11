package edu.se309.app.backend.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.se309.app.backend.service.interfaces.BaseService;

public class BaseController<T, ID extends Serializable, S extends BaseService<T, ID>> {

  private S s;

  public BaseController(S s) {
    this.s = s;
  }

  @GetMapping("/count")
  public long count() {
    return s.count();
  }

  @DeleteMapping("/{id}")
  String deleteById(@PathVariable("id") ID id) {
    s.deleteById(id);
    String className = this.getClass().getSimpleName();
    className = className.substring(0, className.lastIndexOf('C'));
    return "Deleted " + className + " with id: " + id.toString();
  }

  @GetMapping
  public List<T> findAll() {
    return s.findAll();
  }

  @GetMapping("/{id}")
  public T findById(@PathVariable ID id) {
    return s.findById(id);
  }

  public S getService() {
    return s;
  }
}
