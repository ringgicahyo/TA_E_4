package apap.tugasakhir.situ.service;

import apap.tugasakhir.situ.model.PengajuanSuratModel;

public interface PengecekkanPengajuanSuratService {
    PengajuanSuratModel findPengajuanSurat(String noSurat);
    PengajuanSuratModel createPengajuanSurat(PengajuanSuratModel pengajuanSurat);
}
