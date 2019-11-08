package apap.tugasakhir.situ.service;

import apap.tugasakhir.situ.model.JenisSuratModel;

import java.util.List;

public interface JenisSuratService {

  boolean addJenisSurat (JenisSuratModel jenisSurat);

  boolean cekKesamaanNamaJenisSurat (String nama);
  List<JenisSuratModel> getListJenisSurat();
}
