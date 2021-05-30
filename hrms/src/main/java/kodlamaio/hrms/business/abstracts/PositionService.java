package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.entities.concretes.Position;

public interface PositionService {
	
	Result add(Position position);
	Result update(Position position);
	Result delete(Position position);
	DataResult<List<Position>> getAll();
	DataResult<Position> get(int id);
	

}
