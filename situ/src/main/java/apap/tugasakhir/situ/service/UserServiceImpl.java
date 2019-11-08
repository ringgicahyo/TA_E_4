package apap.tugasakhir.situ.service;

import apap.tugasakhir.situ.model.UserModel;
import apap.tugasakhir.situ.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDb userDb;

    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }

    @Override
    public UserModel getUserByUsername(String username) {
        return userDb.findByUsername(username);
    }

    @Override
    public boolean comparePasswordAgainstDatabase(UserModel user, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, user.getPassword());
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public boolean checkPasswordValidity(String password) {
        boolean eightChar = password.length()>=8;
        boolean containsAlphabet = password.matches(".*[a-zA-Z]+.*");
        boolean containsNumber = password.matches(".*\\d.*");

        if (eightChar && containsAlphabet && containsNumber) {
            return true;
        }

        else {
            return false;
        }
    }

    @Override
    public UserModel updatePassword(UserModel user, String password) {
        password = encrypt(password);

        user.setPassword(password);
        userDb.save(user);

        return user;
      
// import org.springframework.stereotype.Service;

// import javax.transaction.Transactional;

// @Transactional
// @Service
// public class UserServiceImpl implements UserService{
//     @Autowired
//     private UserDb userDb;
//     @Override
//     public UserModel getUserById(Integer id) {
//         return userDb.getById(id);
//     }
}
