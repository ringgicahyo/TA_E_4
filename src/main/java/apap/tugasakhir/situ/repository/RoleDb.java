package apap.tugasakhir.situ.repository;

import apap.tugasakhir.situ.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDb extends JpaRepository<RoleModel, Long> {
    RoleModel findByNama(String nama);
}
