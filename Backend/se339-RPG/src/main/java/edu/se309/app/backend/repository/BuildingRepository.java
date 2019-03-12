package edu.se309.app.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.se309.app.backend.entity.Building;

public interface BuildingRepository extends BaseRepository<Building,Integer>{
 
	
	@Query(value = "SELECT earned_stat FROM building_locations as b WHERE (ST_WITHIN(ST_SRID(POINT(-93.644102, 42.029668), 4326), b.geo))", nativeQuery=true)	
	String findEarnedStatFromLocation (@Param("longitude") String longitude, @Param("latitude") String latitude);		
	
//	@Query("INSERT INTO hillcresttooldie (material_number,material_thickness,material_size,lb_per_sheet,dollar_per_lb) VALUES (:number, :thickness, :size, :lbPerSheet, :dollarPerLb)")
//	void uploadMaterialData(String number, BigDecimal thickness, String size, BigDecimal lbPerSheet, BigDecimal dollarPerLb)
}
