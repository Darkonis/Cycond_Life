package edu.se309.app.backend.rest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * Building Repository with custom methods
 */
@Repository
public class BuildingRepositoryCustom {

    private EntityManager entityManager;

    /**
     * Constructor that takes in an entityManager
     * @param entityManager entityManager associated with this repository
     */
    @Autowired
    public BuildingRepositoryCustom(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Finding building's associated Stat
     * @param longitude Longitude
     * @param latitude Latitude
     * @return Stat associated with location
     */
    public String findBuildingStat(String longitude, String latitude) {

        String queryStr = "SELECT earned_stat FROM building_locations as b WHERE (ST_WITHIN(ST_SRID(POINT(" + longitude + "," + latitude + "), 4326), b.geo))";
        try {
            String earnedStat = (String) entityManager.createNativeQuery(queryStr).getSingleResult();
            return earnedStat;
        } catch (NoResultException e) {
            return "none";
        }

    }

    /**
     * Find building's name
     * @param longitude longitude
     * @param latitude latitude
     * @return The associated building's name
     */
    public String findBuildingName(String longitude, String latitude) {
        String queryStr = "SELECT building_name FROM building_locations as b WHERE (ST_WITHIN(ST_SRID(POINT(" + longitude + "," + latitude + "), 4326), b.geo))";
        try {
            String buildingName = (String) entityManager.createNativeQuery(queryStr).getSingleResult();
            if (buildingName == null) {
                return "none";
            }
            return buildingName;
        } catch (NoResultException e) {
            return "none";
        }
    }
}
