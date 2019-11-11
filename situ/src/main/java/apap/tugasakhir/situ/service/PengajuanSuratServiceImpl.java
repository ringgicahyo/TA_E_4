package apap.tugasakhir.situ.service;

import apap.tugasakhir.situ.model.PengajuanSuratModel;
import apap.tugasakhir.situ.repository.PengajuanSuratDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
    public PengajuanSuratModel getPengajuanSurat(Integer id){
        return pengajuanSuratDb.getById(id);
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
    public String ubahPengajuanSurat(PengajuanSuratModel pengajuanSurat) {
        PengajuanSuratModel targetUbah = pengajuanSuratDb.getById(pengajuanSurat.getId());

        try{
            System.out.println("test : " + pengajuanSurat.getStatusUpdate());

            if(pengajuanSurat.getStatusUpdate().equals("Disetujui")){
                Date date = new Date();
                targetUbah.setTanggalDisetujui(date);
                generateNomorSurat(targetUbah);
                System.out.println(targetUbah.getNoSurat());
            }
            targetUbah.setStatus(pengajuanSurat.getStatusUpdate());
            pengajuanSuratDb.save(targetUbah);
            return "Berhasil ubah";
        }catch (NullPointerException nullException){
            return "Gagal ubah";
        }
    }


    public void generateNomorSurat (PengajuanSuratModel pengajuanSurat){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(pengajuanSurat.getTanggalDisetujui());
        String[] dateSplit = date.split("-");
        date = dateSplit[2] + dateSplit[1] + dateSplit[0].substring(2,4);

        String kode =  randomString() + date;
        pengajuanSurat.setNoSurat(kode);
    }

    public String randomString(){
        String characters = "1234567890";
        StringBuilder sb = new StringBuilder(8);
        Random random = new SecureRandom();
        for (int i = 0; i < 8; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

}
