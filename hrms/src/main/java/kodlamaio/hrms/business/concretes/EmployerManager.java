package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.regex.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.constants.Messages;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.core.utilities.validation.Injection;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.EmailVerificationCode;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.User;

@Service
public class EmployerManager  implements EmployerService{

	@Autowired
	private EmployerDao employerDao;
	private VerificationCodeService verificationCodeService;
	private UserService userService;
	
	public EmployerManager(EmployerDao employerDao, VerificationCodeService verificationCodeService,
			UserService userService) {
		super();
		this.employerDao = employerDao;
		this.verificationCodeService = verificationCodeService;
		this.userService = userService;
	}
	
	@Override
	public DataResult<Employer> add(Employer employer) {
		
		Result engine = Injection.run(
				companyNameChecker(employer),webSiteChecker(employer),passwordNullChecker(employer),
				isRealEmployer(employer),isRealPhoneNumber(employer),isEmailAlreadyRegistered(employer),
				isPasswordsMatch(employer));
		
		if(!engine.isSuccess()) {
			return new ErrorDataResult<Employer>(null,engine.getMessage());
		}
		
		User savedUser = this.userService.add(employer);
		this.verificationCodeService.generateCode(new EmailVerificationCode(),savedUser.getUserId());
		return new SuccessDataResult<Employer>(this.employerDao.save(employer),
				"İş Veren Hesabı Sisteme Eklendi, Doğrulama Kodu Gönderildi ID:"+employer.getUserId());
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		
		return new SuccessDataResult<List<Employer>>
		(this.employerDao.findAll(),"İş verenler listelendi.");
	}

	private Result companyNameChecker(Employer employer) {
		if(employer.getCompanyName().isBlank() || employer.getCompanyName() == null) {
			return new ErrorResult("Şirket Adı Doldurulmak Zorundadır");
		}
		return new SuccessResult();
	}
	
	private Result webSiteChecker(Employer employer) {
		if(employer.getWebSiteName().isBlank() || employer.getWebSiteName() == null) {
			return new ErrorResult("WebSite Adresi Doldurulmak Zorundadır");
		}
		return new SuccessResult();
	}
	
	private Result isRealEmployer(Employer employer) {
		 String regex = "^(.+)@(.+)$";
	     Pattern pattern = Pattern.compile(regex);
	     Matcher matcher = pattern.matcher(employer.getEmail());
	     if(!matcher.matches()) {
	    	 return new ErrorResult("Geçersiz Email Adresi");
	     }
	     else if(!employer.getEmail().contains(employer.getWebSiteName())) {
	    	 return new ErrorResult("Domain adresi girmek zorundasınız"); 
	     }
	 	return new SuccessResult();
	     
	}
	
	private Result isEmailAlreadyRegistered(Employer employer) {
		if(employerDao.findAllByEmail(employer.getEmail()).stream().count() != 0) {
			 return new ErrorResult("Email zaten kayıtlı"); 
		}
	 	return new SuccessResult();
	}
	
	private Result passwordNullChecker(Employer employer) {
		if(employer.getPassword().isBlank() || employer.getPassword() == null) {
			 return new ErrorResult("Şifre Bilgisi Doldurulmak zorundadır"); 
		}
		return new SuccessResult();
	}
	
	private Result isPasswordsMatch(Employer user) {
		if (!user.getPassword().equals(user.getPasswordRepeat())) {
			return new ErrorResult(Messages.passwordsNotMatched);
		}
		
		return new SuccessResult();
	}

	private Result isRealPhoneNumber(Employer employer) {
		String patterns 
	      = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$" 
	      + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$" 
	      + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
		
		Pattern pattern = Pattern.compile(patterns);
		Matcher matcher = pattern.matcher(employer.getPhoneNumber());
		if(!matcher.matches()) {
			 return new ErrorResult("Geçersiz Telefon Numarası"); 
		}
		return new SuccessResult();
		
	}
	
	

}
