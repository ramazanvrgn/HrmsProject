package kodlamaio.hrms.entities.concretes;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="job_positions")
public class Position {

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@Column(name="position_name")
	private String positionName;
	@Column(name="description")
	private String description;
	
	public Position() {}
	
	public Position(int id, String positionName, String description) {
		super();
		this.id = id;
		this.positionName = positionName;
		this.description = description;
	}
	
	
}
