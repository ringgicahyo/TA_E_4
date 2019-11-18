package apap.tugasakhir.situ.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugasakhir.situ.model.JenisSuratModel;
import apap.tugasakhir.situ.repository.JenisSuratDb;

@Service
public class JenisSuratServiceImpl implements JenisSuratService {

  @Autowired
  JenisSuratDb jenisSuratDb;

  @Override
  public boolean addJenisSurat(JenisSuratModel jenisSurat) {
    
    if(cekKesamaanNamaJenisSurat(jenisSurat.getNama())) {
      return false;
    }

    jenisSuratDb.save(jenisSurat);
    return true;
  }

  @Override
  public boolean cekKesamaanNamaJenisSurat(String nama) {
    List<JenisSuratModel> listJenisSurat = jenisSuratDb.findAll();

    for(JenisSuratModel x : listJenisSurat) {
      if(x.getNama().equals(nama)) {
        return true;
      }
    }

    return false;
  }

  @Override
  public List<JenisSuratModel> getListJenisSurat() {
    return jenisSuratDb.findAll();
  }

  @Override
  public JenisSuratModel getJenisSurat(Integer id) {
    return jenisSuratDb.getById(id);
  }

  @Override
  public boolean deleteJenisSurat(JenisSuratModel jenisSurat) {
    jenisSuratDb.delete(jenisSurat);
    return true;
  }
}