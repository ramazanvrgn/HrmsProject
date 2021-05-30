package kodlamaio.hrms.core.utilities.validation;
import kodlamaio.hrms.core.utilities.results.*;

public class Injection {
 
	public static Result run(Result... logics) {
        for (Result logic : logics) {
            if (!logic.isSuccess()){
                return logic;
            }
        }
        return new SuccessResult();
	}

	public static Result run(Result firstNameChecker, Result lastNameChecker, boolean realPerson, Result idChecker,
			Result birthDateChecker, Result emailNullChecker, Result realEmail, Result passwordNullChecker,
			Result mailRegistered,Result isIdRegistered,Result isPasswordsMatch) {
		
		return new Result(realPerson);
	}
}
