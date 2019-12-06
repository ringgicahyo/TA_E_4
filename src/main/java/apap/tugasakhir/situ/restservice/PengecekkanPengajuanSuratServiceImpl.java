package apap.tugasakhir.situ.restservice;

import apap.tugasakhir.situ.model.PengajuanSuratModel;
import apap.tugasakhir.situ.repository.PengajuanSuratDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class PengecekkanPengajuanSuratServiceImpl implements PengecekkanPengajuanSuratService{
    @Autowired
    private PengajuanSuratDb pengajuanSuratDb;

    @Override
    public PengajuanSuratModel findPengajuanSurat(String noSurat){
        PengajuanSuratModel pengajuanSurat = pengajuanSuratDb.findByNoSurat(noSurat);
        if(pengajuanSurat != null){
            return pengajuanSurat;
        }else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public PengajuanSuratModel createPengajuanSurat(PengajuanSuratModel pengajuanSurat) {
        return pengajuanSuratDb.save(pengajuanSurat);
    }

    @Override
    public Map<String, Object> getUserData(String uuid) {
        // Retrieve semua idUser di SI-Sivitas
            WebClient webClient = WebClient.builder().baseUrl("http://sivitas.herokuapp.com/api/employees/").build();
        Map<String, Object> userSivitasByRole =
                webClient.get().uri("/uuid").retrieve().bodyToMono(Map.class).block();
        return userSivitasByRole;
    }
}
