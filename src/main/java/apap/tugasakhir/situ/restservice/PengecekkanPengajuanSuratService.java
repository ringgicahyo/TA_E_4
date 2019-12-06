package apap.tugasakhir.situ.restservice;

import apap.tugasakhir.situ.model.PengajuanSuratModel;

import java.util.Map;

public interface PengecekkanPengajuanSuratService {
    PengajuanSuratModel findPengajuanSurat(String noSurat);
    PengajuanSuratModel createPengajuanSurat(PengajuanSuratModel pengajuanSurat);
    Map<String, Object> getUserData(String id);

}
