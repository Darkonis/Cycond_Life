package edu.se309.app.backend.repository;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Repository;

import edu.se309.app.backend.entity.BuildingLocation;
import edu.se309.app.backend.repository.interfaces.BuildingLocationRepository;

@Repository
public class BuildingLocationRepositoryImplementation implements BuildingLocationRepository {
	private EntityManager entityManager;	
	
	@Autowired
	public BuildingLocationRepositoryImplementation(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public BuildingLocation findBuildingStat(Point point) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<BuildingLocation> query = 
				currentSession.createQuery("FROM buildingLocations a WHERE WITHIN(:point, a.geo) = true", BuildingLocation.class)
				.setParameter("point", point);
		
		BuildingLocation buildingLocationsOutput = query.getSingleResult();
		return buildingLocationsOutput;		
		
	}
}
