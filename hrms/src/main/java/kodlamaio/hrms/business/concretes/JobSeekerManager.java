package kodlamaio.hrms.business.concretes;

import java.rmi.RemoteException;
import java.util.List;
import java.util.regex.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.UserCheckService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.constants.Messages;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.core.utilities.validation.Injection;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.entities.concretes.EmailVerificationCode;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.User;


@Service
public class JobSeekerManager implements JobSeekerService {

	private JobSeekerDao jobSeekerDao;
	private UserService userService;
	private VerificationCodeService verificationCodeService;
	private UserCheckService userCheck;
	
	@Autowired
	public JobSeekerManager(JobSeekerDao jobSeekerDao,UserService userService,UserCheckService userCheck,VerificationCodeService verificationCodeService) {
		super();
		this.jobSeekerDao = jobSeekerDao;
		this.userService = userService;
		this.userCheck= userCheck;
		this.verificationCodeService=verificationCodeService;
	}

	@Override
	public DataResult<List<JobSeeker>> getAll() {
		
		return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.findAll(),"Listed All Job Seekers.");
	}

	@Override
	public DataResult<JobSeeker> add(JobSeeker user) throws NumberFormatException, RemoteException {
		Result engine = Injection.run(firstNameChecker(user),lastNameChecker(user),
				userCheck.CheckRealPerson(user),
				IdChecker(user),
				birthDateChecker(user),
				emailNullChecker(user),
				isRealEmail(user),
				passwordNullChecker(user),
				isMailRegistered(user),
				isIdRegistered(user),
				isPasswordsMatch(user)
				);
		if(!engine.isSuccess()) {
			return new ErrorDataResult<JobSeeker>(null,engine.getMessage());
		}
		
		User savedUser = this.userService.add(user);
		this.verificationCodeService.generateCode(new EmailVerificationCode(),savedUser.getUserId());
		return new SuccessDataResult<JobSeeker>
		(this.jobSeekerDao.save(user),Messages.isRegisterSuccessForCandidateMessage);
		
		
	}
	
	private Result firstNameChecker(JobSeeker user) {
		if(user.getFirstName().isBlank() || user.getFirstName().equals(null)) {
			return new ErrorResult(Messages.requiredFirstName);
			
		}
		return new SuccessResult();
	}
	
	private Result lastNameChecker(JobSeeker user) {
		if(user.getLastName().isBlank() || user.getLastName().equals(null)) {
			return new ErrorResult(Messages.requiredLastName);
		}
		return new SuccessResult();
	}
	
	private Result birthDateChecker(JobSeeker user) {
		if(user.getBirthDate().equals(null)) {
			return new ErrorResult(Messages.requiredBirthDate);
		}
		return new SuccessResult();
	}
	
	private Result emailNullChecker(JobSeeker user) {
		if(user.getEmail().isBlank() || user.getEmail().equals(null)) {
			return new ErrorResult(Messages.requiredEmail);
		}
		return new SuccessResult();
	}
	
	private Result passwordNullChecker(JobSeeker user) {
		if(user.getPassword().isBlank() || user.getPassword().equals(null)) {
			return new ErrorResult(Messages.requiredPassword);
		}
		return new SuccessResult();
	}
	
	private Result isRealEmail(JobSeeker user) {
		 String regex = "^(.+)@(.+)$";
	     Pattern pattern = Pattern.compile(regex);
	     Matcher matcher = pattern.matcher(user.getEmail());
	     if(!matcher.matches()) {
	    		return new ErrorResult(Messages.isRealMail);
	     }
	     return new SuccessResult();
	     
	}
	
	private Result IdChecker(JobSeeker user) {
		if(user.getNationalIdentity().isBlank()) {
			return new ErrorResult(Messages.requiredId);
		}
		
		 return new SuccessResult();
	}
	
	private Result isMailRegistered(JobSeeker user) {
		if(jobSeekerDao.findByEmail(user.getEmail()).stream().count() != 0) {
			return new ErrorResult(Messages.alreadyRegisteredMail);
		}
		 return new SuccessResult();
	}
	
	private Result isIdRegistered(JobSeeker user) {
		if(jobSeekerDao.findAllByNationalIdentiy
				(user.getNationalIdentity()).stream().count() != 0 ) {
			return new ErrorResult(Messages.alreadyRegisteredId);
		}
		 return new SuccessResult(); 
	}
	
	private Result isPasswordsMatch(JobSeeker user) {
		if (!user.getPassword().equals(user.getPasswordRepeat())) {
			return new ErrorResult(Messages.passwordsNotMatched);
		}
		
		return new SuccessResult();
	}

}
