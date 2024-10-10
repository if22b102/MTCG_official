package at.fhtw.sampleapp.Repos;


import at.fhtw.sampleapp.dal.UnitOfWork;
import at.fhtw.sampleapp.model.UserStore;

public class UserRepository {
    private UnitOfWork unitOfWork;
    private UserStore userStore = UserStore.getInstance();

    public UserRepository(UnitOfWork unitOfWork)
    {
        this.unitOfWork = unitOfWork;
    }

    public boolean regUser(String username, String password) throws Exception {
        try {
            if (!userStore.userExists(username)) {
                userStore.addUser(username, password);
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public boolean userLogin(String username, String password) throws Exception {
        try {
            if (userStore.userExists(username) && (userStore.getPassword(username) == password)) {
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
