package apap.tugasakhir.situ.service;

import apap.tugasakhir.situ.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user);
    public String encrypt(String password);
    UserModel getUserByUsername(String username);
    UserModel updatePassword(UserModel user, String password);
    void deleteUser(UserModel user);

    boolean comparePasswordAgainstDatabase(UserModel user, String password);
    boolean checkPasswordValidity(String password);
}
