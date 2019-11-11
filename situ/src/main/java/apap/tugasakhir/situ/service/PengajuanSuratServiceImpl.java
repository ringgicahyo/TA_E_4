package apap.tugasakhir.situ.service;

import apap.tugasakhir.situ.model.PengajuanSuratModel;
import apap.tugasakhir.situ.repository.PengajuanSuratDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional
public class PengajuanSuratServiceImpl implements PengajuanSuratService{
    @Autowired
    private PengajuanSuratDb pengajuanSuratDb;

    @Override
    public List<PengajuanSuratModel> getListPengajuanSurat() {
        return pengajuanSuratDb.findAll();
    }

    @Override
    public PengajuanSuratModel addPengajuanSurat(PengajuanSuratModel pengajuanSurat) {
        return pengajuanSuratDb.save(pengajuanSurat);
    }

    @Override
    public String deletePengajuanSurat(Integer id) {
        PengajuanSuratModel pengajuanSurat = pengajuanSuratDb.getById(id);
        if (pengajuanSurat.getStatus().equals("Menunggu Persetujuan")){
            pengajuanSuratDb.delete(pengajuanSurat);
//            System.out.println("Test masuk sini");
            return "Delete";
        }
        return "Fail Delete";
    }

    @Override
    public PengajuanSuratModel ubahPengajuanSurat(PengajuanSuratModel pengajuanSurat) {
//        PengajuanSuratModel targetUbah = pengajuanSuratDb.findById(pengajuanSurat.getIdPengajuanSurat()).get();
//        try{
//            targetUbah.setNamaPasien(pasien.getNamaPasien());
//            targetUbah.setTempatLahir(pasien.getTempatLahir());
//
//            //Cek tanggal lahirnya, berganti atau tidak
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            String pasienBaru = formatter.format(pasien.getTanggalLahir());
//            String pasienLama = formatter.format(targetPasien.getTanggalLahir());
//
//            //Jika berganti maka set dan generate kode baru
//            if (!pasienBaru.equals(pasienLama)){
//                targetPasien.setTanggalLahir(pasien.getTanggalLahir());
//                generateCodePasien(targetPasien);
//            }
//            pasienDb.save(targetPasien);
//            return targetPasien;
//        }catch (NullPointerException nullException){
//            return null;
//        }
        return pengajuanSurat;
    }

}
