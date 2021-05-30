package kodlamaio.hrms.business.abstracts;

import java.rmi.RemoteException;

import kodlamaio.hrms.entities.concretes.JobSeeker;

public interface UserCheckService {

	boolean CheckRealPerson(JobSeeker user) throws NumberFormatException, RemoteException;
}
