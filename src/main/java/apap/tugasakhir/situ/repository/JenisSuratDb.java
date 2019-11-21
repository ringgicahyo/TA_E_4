package apap.tugasakhir.situ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugasakhir.situ.model.JenisSuratModel;

@Repository
public interface JenisSuratDb extends JpaRepository<JenisSuratModel, Integer> {
    JenisSuratModel getById(Integer id);
}
