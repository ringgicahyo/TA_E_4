package apap.tugasakhir.situ.service;

import apap.tugasakhir.situ.model.LowonganModel;
import apap.tugasakhir.situ.repository.LowonganDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LowonganServiceImpl implements LowonganService {
    @Autowired
    private LowonganDb lowonganDb;

    @Override
    public void addLowongan(LowonganModel lowongan) {
        lowonganDb.save(lowongan);
    }

    @Override
    public List<LowonganModel> getLowonganList() {
        return lowonganDb.findAll();
    }

    @Override
    public LowonganModel updateLowongan(LowonganModel lowongan) {
        LowonganModel targetLowongan = lowonganDb.findById(lowongan.getId()).get();

        try {
            targetLowongan.setJumlah(lowongan.getJumlah());
            return targetLowongan;
        } catch (NullPointerException e){
            return null;
        }
    }

    @Override
    public Optional<LowonganModel> getLowonganById(Integer id) {
        return lowonganDb.findById(id);
    }
}
