package apap.tugasakhir.situ.service;

import apap.tugasakhir.situ.model.UserModel;
import apap.tugasakhir.situ.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDb userDb;
    @Override
    public UserModel getUserById(Integer id) {
        return userDb.getById(id);
    }
}
