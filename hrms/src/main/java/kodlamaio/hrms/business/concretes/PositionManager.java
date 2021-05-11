package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.PositionService;
import kodlamaio.hrms.dataAccess.abstracts.PositionDao;
import kodlamaio.hrms.entities.concretes.Position;

@Service
public class PositionManager implements PositionService {

	private PositionDao positionDao;
	
	@Autowired
	public PositionManager(PositionDao positionDao) {
		super();
		this.positionDao = positionDao;
	}

	@Override
	public void add(Position position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Position position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Position position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Position> getAll() {
		
		return positionDao.findAll();
	}

	@Override
	public Position get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
