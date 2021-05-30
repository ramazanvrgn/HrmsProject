package kodlamaio.hrms.core.adapters;


import java.rmi.RemoteException;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.UserCheckService;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisAdapter implements UserCheckService {


	@SuppressWarnings("deprecation")
	@Override
	public boolean CheckRealPerson(JobSeeker user) throws NumberFormatException, RemoteException {
		KPSPublicSoapProxy kpsPublic = new KPSPublicSoapProxy();
		
		boolean result = kpsPublic.TCKimlikNoDogrula(Long.parseLong(user.getNationalIdentity()),user.getFirstName().toUpperCase() , 
				user.getLastName().toUpperCase(), user.getBirthDate().getYear());
		return result;
	}
}
