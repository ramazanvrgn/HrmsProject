package kodlamaio.hrms.api.contrellers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import kodlamaio.hrms.business.abstracts.PositionService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.entities.concretes.Position;


@RestController
@RequestMapping("/api/positions")
public class PositionsController {

	private PositionService positionService;

	@Autowired
	public PositionsController(PositionService positionService) {
		super();
		this.positionService = positionService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<Position>> getAll(){
		
		return this.positionService.getAll();
	}
	
	
	
}
