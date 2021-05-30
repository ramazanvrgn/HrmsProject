package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import kodlamaio.hrms.entities.concretes.Position;

public interface PositionDao extends JpaRepository<Position, Integer> {

	@Query("select u from JobTitle u where lower(u.position_name) like lower(concat('%', :position_name,'%'))")
	List<Position> findJobPosition(@Param("position_name") String positionName);
 
 	List<Position> findById(@Param("") int id);

 	Optional<Position> findOneByPosition(@Param("") String positionName);

 	List<Position> findAllByPosition(String positionName);
}
