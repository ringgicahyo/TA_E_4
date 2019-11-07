package apap.tugasakhir.situ.service;

import apap.tugasakhir.situ.model.JenisSuratModel;
import apap.tugasakhir.situ.model.PengajuanSuratModel;
import apap.tugasakhir.situ.repository.JenisSuratDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JenisSuratServiceImpl implements JenisSuratService{
    @Autowired
    private JenisSuratDb jenisSuratDb;

    @Override
    public List<JenisSuratModel> getListJenisSurat() {
        return jenisSuratDb.findAll();
    }

}
