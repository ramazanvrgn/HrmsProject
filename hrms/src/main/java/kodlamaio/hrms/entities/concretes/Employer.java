package kodlamaio.hrms.entities.concretes;

import javax.persistence.*;

import lombok.*;


@EqualsAndHashCode(callSuper=true)  
@Data
@Entity
@Table(name="employers")
@AllArgsConstructor
@NoArgsConstructor
public class Employer extends User {

	@Column(name="company_name")
	private String companyName;
	@Column(name="phone_number")
	private String phoneNumber;
	@Column(name="web_site_name")
	private String webSiteName;
	

}
