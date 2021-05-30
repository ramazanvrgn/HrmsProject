package kodlamaio.hrms.core.utilities.results;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Result {

	private boolean success;
	private String message;
	
	public Result(boolean success) {
		
		this.success=success;
	}
	
}
