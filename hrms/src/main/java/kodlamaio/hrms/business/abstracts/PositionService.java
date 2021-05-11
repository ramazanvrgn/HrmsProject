package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.entities.concretes.Position;

public interface PositionService {
	
	void add(Position position);
	void update(Position position);
	void delete(Position position);
	List<Position> getAll();
	Position get(int id);
	

}
