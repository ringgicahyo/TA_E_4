package apap.tugasakhir.situ.service;

import apap.tugasakhir.situ.model.RoleModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    List<RoleModel> findAll();
    RoleModel findByNama(String nama);
    void addRole(RoleModel role);
}
