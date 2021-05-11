package kodlamaio.hrms.entities.concretes;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="employers")
public class Employer {

	@Id
	@GeneratedValue
	@Column(name="user_id")
	private int userId;
	@Column(name="company_name")
	private String companyName;
	@Column(name="phone_number")
	private String phoneNumber;
	@Column(name="web_site_name")
	private String webSiteName;
	
	public Employer() {}

	public Employer(int userId, String companyName, String phoneNumber, String webSiteName) {
		super();
		this.userId = userId;
		this.companyName = companyName;
		this.phoneNumber = phoneNumber;
		this.webSiteName = webSiteName;
	}
}
