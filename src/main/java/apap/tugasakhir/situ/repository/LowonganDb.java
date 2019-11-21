package apap.tugasakhir.situ.repository;

import apap.tugasakhir.situ.model.LowonganModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LowonganDb extends JpaRepository<LowonganModel, Integer> {
    Optional<LowonganModel> findById(Integer id);
    List<LowonganModel> findAll();
}
