//package edu.se309.app.backend.repository;
//
//import javax.persistence.EntityManager;
//
//import org.hibernate.Session;
//import org.hibernate.query.Query;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.geo.Point;
//import org.springframework.stereotype.Repository;
//
//import edu.se309.app.backend.entity.Building;
//
//@Repository
//public class BuildingRepositoryImplementation implements BuildingRepository {
//
//  private EntityManager entityManager;
//
//  @Autowired
//  public BuildingRepositoryImplementation(EntityManager entityManager) {
//    this.entityManager = entityManager;
//  }
//
//  @Override
//  public Building findBuildingStat(Point point) {
//    Session currentSession = entityManager.unwrap(Session.class);
//    Query<Building> query = currentSession
//      .createQuery("FROM buildingLocations a WHERE WITHIN(:point, a.geo) = true", Building.class)
//      .setParameter("point", point);
//    Building buildingLocationsOutput = query.getSingleResult();
//    return buildingLocationsOutput;
//  }
//}
