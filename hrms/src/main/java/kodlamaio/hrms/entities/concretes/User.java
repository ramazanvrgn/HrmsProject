package kodlamaio.hrms.entities.concretes;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue
	@Column(name="user_id")
	private int userId;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="password_repeat")
	private String passwordRepeat;
	@Column(name="status")
	private boolean status;
	
	public User() {}
	
	public User(int user_id, String email, String password, String password_repeat, boolean status) {
		super();
		this.userId = user_id;
		this.email = email;
		this.password = password;
		this.passwordRepeat = password_repeat;
		this.status = status;
	}
}
