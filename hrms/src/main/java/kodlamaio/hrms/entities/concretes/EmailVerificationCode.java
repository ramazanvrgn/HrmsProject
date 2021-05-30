package kodlamaio.hrms.entities.concretes;

import javax.persistence.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "verification_codes")
public class EmailVerificationCode {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="code")
	private String code;
	
	@Column(name="is_verified")
	private boolean isVerified;
	
	public boolean isVerified() {
		return isVerified;
	}
	
	@Column(name="user_id")
	private int userId;
}
