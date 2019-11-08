package apap.tugasakhir.situ.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugasakhir.situ.model.JenisLowonganModel;
import apap.tugasakhir.situ.repository.JenisLowonganDb;

@Service
public class JenisLowonganServiceImpl implements JenisLowonganService {

  @Autowired
  JenisLowonganDb jenisLowonganDb;

  @Override
  public boolean addJenisLowongan(JenisLowonganModel jenisLowongan) {
    if(cekKesamaanNamaJenisLowongan(jenisLowongan.getNama())) {
      return false;
    }

    jenisLowonganDb.save(jenisLowongan);
    return true;
  }

  @Override
  public boolean cekKesamaanNamaJenisLowongan(String nama) {
    List<JenisLowonganModel> listJenisLowongan = jenisLowonganDb.findAll();

    for(JenisLowonganModel x : listJenisLowongan) {
      if(x.getNama().equals(nama)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public List<JenisLowonganModel> getAllJenisLowongan() {    
    return jenisLowonganDb.findAll();
  }
}