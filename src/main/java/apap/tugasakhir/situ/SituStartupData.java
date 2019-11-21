package apap.tugasakhir.situ;

import apap.tugasakhir.situ.model.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import apap.tugasakhir.situ.service.RoleService;
import apap.tugasakhir.situ.service.UserService;
import apap.tugasakhir.situ.model.UserModel;

@Component
public class SituStartupData implements ApplicationRunner {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(roleService.findByNama("Admin TU") == null){
            RoleModel role = new RoleModel();
            role.setNama("Admin TU");
            roleService.addRole(role);
        }
        if(userService.getUserByUsername("admin") == null){
            UserModel user = new UserModel();
            user.setUsername("admin");
            user.setPassword("admin1234");
            user.setRole(roleService.findByNama("Admin TU"));
            userService.addUser(user);
        }
    }
}
