package apap.tugasakhir.situ.service;

import apap.tugasakhir.situ.model.JenisLowonganModel;

import java.util.List;

public interface JenisLowonganService {

  boolean addJenisLowongan(JenisLowonganModel jenisLowongan);

  boolean cekKesamaanNamaJenisLowongan(String nama);

  List<JenisLowonganModel> getAllJenisLowongan();

  JenisLowonganModel getJenisLowongan(Integer id);

  boolean deleteJenisLowongan(JenisLowonganModel jenisLowongan);
}