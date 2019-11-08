package apap.tugasakhir.situ.repository;

import apap.tugasakhir.situ.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDb extends JpaRepository<UserModel, Long> {
}
