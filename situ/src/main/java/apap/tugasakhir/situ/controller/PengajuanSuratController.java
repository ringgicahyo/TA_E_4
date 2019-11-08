package apap.tugasakhir.situ.controller;

import apap.tugasakhir.situ.model.JenisSuratModel;
import apap.tugasakhir.situ.model.PengajuanSuratModel;
import apap.tugasakhir.situ.model.UserModel;
import apap.tugasakhir.situ.service.JenisSuratService;
import apap.tugasakhir.situ.service.PengajuanSuratService;
import apap.tugasakhir.situ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PengajuanSuratController {
    @Autowired
    private PengajuanSuratService pengajuanSuratService;
    @Autowired
    private JenisSuratService jenisSuratService;
    @Autowired
    private UserService userService;

    //URL daftar pengajuan surat atau beranda
    @RequestMapping(value="/daftar-pengajuan-surat/{username}", method = RequestMethod.GET)
    public String home(@PathVariable String username, Model model) {
        UserModel user = userService.getUserByUsername(username);
        List<PengajuanSuratModel> listPengajuan = new ArrayList<>();
        if(user.getRole().getNama().equals("Admin TU")){
            //Membuat list yang menampung objek semua pasien yang ada pada database
            pengajuanSuratService.getListPengajuanSurat();
        }else {
            user.getListPengajuanSurat();
        }
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
        model.addAttribute("title", "Menambah Pengajuan Surat");

        return "form-pengajuan-surat";
    }

    //URL mapping yang digunakan untuk mensubmit form halaman add pasien
    @RequestMapping(value = "/pengajuan-surat/tambah", method = RequestMethod.POST)
    public String addPasienSubmit(@ModelAttribute PengajuanSuratModel pengajuanSurat, Model model) {
        JenisSuratModel jenisSurat = jenisSuratService.getJenisSurat(pengajuanSurat.getIdJenisSurat());

        pengajuanSurat.setNoSurat("0");
        pengajuanSurat.setTanggalDisetujui(null);
        System.out.println(pengajuanSurat.getKeterangan());
        Date date = new Date();
        pengajuanSurat.setTanggalPengajuan(date);

        pengajuanSurat.setJenisSurat(jenisSurat);
        System.out.println(pengajuanSurat.getJenisSurat());
        System.out.println(pengajuanSurat.getTanggalPengajuan());

        pengajuanSurat.setStatus("Menunggu Persetujuan");
        System.out.println(pengajuanSurat.getStatus());

        // UserModel user = userService.getUserById(1);
        // pengajuanSurat.setUser(user);

        System.out.println(pengajuanSurat.getUser());
        System.out.println(pengajuanSurat.getIdJenisSurat());

        pengajuanSuratService.addPengajuanSurat(pengajuanSurat);
        model.addAttribute("pengajuanSurat", pengajuanSurat);
        model.addAttribute("title", "Menambah Pengajuan Surat");

        return "pengajuan-surat-response";
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
