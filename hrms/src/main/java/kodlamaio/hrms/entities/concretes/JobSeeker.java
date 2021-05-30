package kodlamaio.hrms.entities.concretes;

import java.util.Date;

import javax.persistence.*;

import lombok.*;

@EqualsAndHashCode(callSuper=true) 
@Data
@Entity
@Table(name="job_seekers")
@AllArgsConstructor
@NoArgsConstructor
public class JobSeeker extends User  {
		
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="identification_number")
	private String nationalIdentity;
	@Column(name="birth_date")
	private Date birthDate;
	
	
	

	
}
