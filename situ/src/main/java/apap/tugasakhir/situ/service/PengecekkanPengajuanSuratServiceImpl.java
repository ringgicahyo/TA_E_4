package apap.tugasakhir.situ.service;

import apap.tugasakhir.situ.model.PengajuanSuratModel;
import apap.tugasakhir.situ.repository.PengajuanSuratDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

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

}
