package apap.tugasakhir.situ.service;

import apap.tugasakhir.situ.model.JenisSuratModel;

public interface JenisSuratService {

  boolean addJenisSurat (JenisSuratModel jenisSurat);

  boolean cekKesamaanNamaJenisSurat (String nama);
}
