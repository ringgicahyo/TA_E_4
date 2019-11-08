package apap.tugasakhir.situ.service;

import apap.tugasakhir.situ.model.LowonganModel;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface LowonganService {
    void addLowongan(LowonganModel lowongan);
    LowonganModel updateLowongan(LowonganModel lowongan);
    Optional<LowonganModel> getLowonganById(Integer id);
}
