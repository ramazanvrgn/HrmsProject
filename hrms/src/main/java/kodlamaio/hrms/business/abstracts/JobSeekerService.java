package kodlamaio.hrms.business.abstracts;

import java.rmi.RemoteException;
import java.util.List;

import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.entities.concretes.*;

public interface JobSeekerService {

	DataResult<JobSeeker> add(JobSeeker user) throws NumberFormatException, RemoteException; 
	
	DataResult<List<JobSeeker>> getAll();
}
