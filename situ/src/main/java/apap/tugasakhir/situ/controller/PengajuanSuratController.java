package apap.tugasakhir.situ.controller;

import apap.tugasakhir.situ.model.JenisSuratModel;
import apap.tugasakhir.situ.model.PengajuanSuratModel;
import apap.tugasakhir.situ.service.JenisSuratService;
import apap.tugasakhir.situ.service.PengajuanSuratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PengajuanSuratController {
    @Autowired
    private PengajuanSuratService pengajuanSuratService;
    @Autowired
    private JenisSuratService jenisSuratService;

    //URL daftar pengajuan surat atau beranda
    @RequestMapping(value="/daftar-pengajuan-surat", method = RequestMethod.GET)
    public String home(Model model) {
        //Membuat list yang menampung objek semua pasien yang ada pada database
        List<PengajuanSuratModel> listPengajuan = pengajuanSuratService.getListPengajuanSurat();

        model.addAttribute("listPengajuan", listPengajuan);
        model.addAttribute("title", "Daftar Pengajuan Surat");
        return "daftar-pengajuan-surat";
    }


    //URL mapping yang digunakan untuk mengakses halaman form menambahkan pengajuan surat
    @RequestMapping(value = "/pengajuan-surat/tambah", method = RequestMethod.GET)
    public String addPengajuanSuratFormPage(Model model) {

        //Membuat objek yang nantinya berguna untuk menampung hasil value yang ada di html
        PengajuanSuratModel pengajuanSurat = new PengajuanSuratModel();

        List<JenisSuratModel> jenisSuratList = jenisSuratService.getListJenisSurat();

        model.addAttribute("jenisSuratList", jenisSuratList);
        model.addAttribute("pengajuanSurat", pengajuanSurat);
        model.addAttribute("title", "Menambah Pengajuan");

        return "form-pengajuan-surat";
    }

//    //Menghapus penyakit yang ada pada daftar diagnosis SIPAS
//    @RequestMapping(value="/pengajuan-surat/hapus/{id}")
//    private String deleteDiagnosis(@PathVariable(value = "id") Long idPengajuanSurat,
//                                   Model model) {
//        String hasilHapus = pengajuanSuratService.deleteDiagnosisPasien(idPengajuanSurat);
//
//        model.addAttribute("title", "Delete Diagnosis Penyakit");
//
//        //Cek apakah berhasil dihapus atau tidak
//        if(hasilHapus.equals("delete")){
//            return "delete-diagnosis-penyakit";
//        }
//        return "delete-fail";
//    }

}
