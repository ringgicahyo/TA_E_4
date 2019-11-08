package apap.tugasakhir.situ.service;

import apap.tugasakhir.situ.model.JenisLowonganModel;

public interface JenisLowonganService {

  boolean addJenisLowongan(JenisLowonganModel jenisLowongan);

  boolean cekKesamaanNamaJenisLowongan(String nama);
}