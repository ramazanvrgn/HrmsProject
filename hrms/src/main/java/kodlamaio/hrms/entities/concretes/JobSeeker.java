package kodlamaio.hrms.entities.concretes;

import java.util.Date;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="job_seekers")
public class JobSeeker {
	
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private int userId;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="national_identity")
	private String nationalIdentity;
	@Column(name="birth_date")
	private Date birthDate;
	
	
	public JobSeeker() {}

	public JobSeeker(int userId, String firstName, String lastName, String nationalIdentity, Date birthDate) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationalIdentity = nationalIdentity;
		this.birthDate = birthDate;
	}
}
