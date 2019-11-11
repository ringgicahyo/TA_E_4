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

    //URL yang akan merujuk pada daftar pengajuan surat
    @RequestMapping(value="/daftar-pengajuan-surat/{username}", method = RequestMethod.GET)
    public String home(@PathVariable String username, Model model) {
        UserModel user = userService.getUserByUsername(username);
        List<PengajuanSuratModel> listPengajuan = new ArrayList<>();
        System.out.println(user.getRole().getNama().equals("Admin TU"));
        System.out.println(user.getRole().getNama());
        System.out.println(user.getUsername());

        if(user.getRole().getNama().equals("Admin TU")){

            //Membuat list yang menampung objek semua pasien yang ada pada database
            listPengajuan = pengajuanSuratService.getListPengajuanSurat();
        }else {
            System.out.println("Test Masuk sini");
            listPengajuan = user.getListPengajuanSurat();
        }
        model.addAttribute("username", user.getRole().getNama());
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

    //URL mapping yang digunakan untuk mensubmit form halaman untuk menambah pengajuan surat
    @RequestMapping(value = "/pengajuan-surat/tambah/{username}", method = RequestMethod.POST)
    public String addPengajuanSuratSubmit(@PathVariable String username,
                                          @ModelAttribute PengajuanSuratModel pengajuanSurat,
                                          Model model) {
        JenisSuratModel jenisSurat = jenisSuratService.getJenisSurat(pengajuanSurat.getIdJenisSurat());

        pengajuanSurat.setNoSurat("0");
        pengajuanSurat.setTanggalDisetujui(null);

        Date date = new Date();
        pengajuanSurat.setTanggalPengajuan(date);

        pengajuanSurat.setJenisSurat(jenisSurat);

        pengajuanSurat.setStatus("Menunggu Persetujuan");

        UserModel user = userService.getUserByUsername(username);
        pengajuanSurat.setUser(user);

        pengajuanSuratService.addPengajuanSurat(pengajuanSurat);
        model.addAttribute("pengajuanSurat", pengajuanSurat);
        model.addAttribute("title", "Menambah Pengajuan Surat");

        return "pengajuan-surat-response";
    }

    //Menghapus pengajuan Surat yang ada pada daftar pengajuan Surat
    @RequestMapping(value="/pengajuan-surat/hapus/{id}")
    private String deleteDiagnosis(@PathVariable(value = "id") Integer idPengajuanSurat, Model model) {
        String hasilHapus = pengajuanSuratService.deletePengajuanSurat(idPengajuanSurat);

        model.addAttribute("title", "Delete Pengajuan Surat");
        model.addAttribute("hasil", hasilHapus);
        return "delete-pengajuan-surat-response";
    }

}
