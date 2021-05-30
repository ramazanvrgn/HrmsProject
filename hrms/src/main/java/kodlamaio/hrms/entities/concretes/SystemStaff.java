package kodlamaio.hrms.entities.concretes;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="system_staffs")
public class SystemStaff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	@Column(name="claim_id")
	private int claimId;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	
	public SystemStaff() {}
	
	public SystemStaff(int userId, int claimId, String firstName, String lastName) {
		super();
		this.userId = userId;
		this.claimId = claimId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
