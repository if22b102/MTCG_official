package at.fhtw.sampleapp.Repos;


import at.fhtw.sampleapp.dal.UnitOfWork;
import at.fhtw.sampleapp.model.UserStore;

public class SessionsRepository {
    private UnitOfWork unitOfWork;
    private UserStore userStore = UserStore.getInstance(); 

    public SessionsRepository(UnitOfWork unitOfWork)
    {
        this.unitOfWork = unitOfWork;
    }

    public boolean userLogin(String username, String password){
        if(userStore.userExists(username) && userStore.getPassword(username).equals(password)) {
            return true;
        }else {
            return false;        }
    }




}

