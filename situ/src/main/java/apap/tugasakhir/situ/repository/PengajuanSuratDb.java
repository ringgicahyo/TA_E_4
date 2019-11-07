package apap.tugasakhir.situ.repository;

import apap.tugasakhir.situ.model.PengajuanSuratModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PengajuanSuratDb extends JpaRepository<PengajuanSuratModel, Long> {
    PengajuanSuratModel getById(Long id);
}
