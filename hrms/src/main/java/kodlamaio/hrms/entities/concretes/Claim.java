package kodlamaio.hrms.entities.concretes;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="claims")
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="claim_name")
	private String claimName;
	
	public Claim () {}
	
	public Claim(int id, String claimName) {
		super();
		this.id = id;
		this.claimName = claimName;
	}
	
	
}
