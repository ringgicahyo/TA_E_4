package apap.tugasakhir.situ.service;

import apap.tugasakhir.situ.model.PengajuanSuratModel;

import java.util.List;

public interface PengajuanSuratService {
    List<PengajuanSuratModel> getListPengajuanSurat();
    PengajuanSuratModel addPengajuanSurat(PengajuanSuratModel pengajuanSurat);
    String deletePengajuanSurat(Long id);
    PengajuanSuratModel ubahPengajuanSurat(PengajuanSuratModel pengajuanSurat);
}
