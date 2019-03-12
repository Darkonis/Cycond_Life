package edu.se309.app.backend.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.se309.app.backend.entity.Building;

@Repository
public class BuildingRepositoryCustom  {

  private EntityManager entityManager;

  @Autowired
  public BuildingRepositoryCustom(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public String findBuildingStat(String longitude, String latitude) {
    
    String queryStr = "SELECT earned_stat FROM building_locations as b WHERE (ST_WITHIN(ST_SRID(POINT("+longitude+","+ latitude +"), 4326), b.geo))";     
    List<Object[]> earnedStat = entityManager.createNativeQuery(queryStr).getResultList();
    if (earnedStat.isEmpty()) {
    	return "none";
    }
    return (String)(((Building) earnedStat.get(0)[0]).getEarnedStat());
  }
}
