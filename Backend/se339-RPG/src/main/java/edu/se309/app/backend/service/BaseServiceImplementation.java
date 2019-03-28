package edu.se309.app.backend.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.hibernate.service.spi.ServiceException;
import org.springframework.transaction.annotation.Transactional;

import edu.se309.app.backend.repository.BaseRepository;
import edu.se309.app.backend.service.interfaces.BaseService;

public class BaseServiceImplementation<T, ID extends Serializable, R extends BaseRepository<T, ID>>
  implements BaseService<T, ID> {

  private R r;

  public BaseServiceImplementation(R r) {
    this.r = r;
  }

  @Override
  @Transactional
  public long count() {
    return r.count();
  }

  @Override
  @Transactional
  public void deleteById(ID id) {
    r.deleteById(id);
  }

  @Override
  @Transactional
  public List<T> findAll() {
    return r.findAll();
  }

  @Override
  @Transactional
  @NotNull
  public T findById(ID id) {
    Optional<T> t = r.findById(id);
    return nullCheck(t, "Invalid request: no such id: " + id);
  }

  public R getRepository() {
    return r;
  }

  @NotNull
  public T nullCheck(Optional<T> t, String errorMessage) {
    if (!t.isPresent()) {
      throw new ServiceException(errorMessage);
    } else {
      return t.get();
    }
  }

  @Override
  @Transactional
  public void save(T entity) {
    r.save(entity);
  }
}
