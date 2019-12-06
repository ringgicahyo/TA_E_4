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

        if(user.getRole().getNama().equals("Admin TU") || user.getRole().getNama().equals("Kepala Sekolah") ){
            listPengajuan = pengajuanSuratService.getListPengajuanSurat();
        }else {
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

        String hasilTambah = pengajuanSuratService.addPengajuanSurat(pengajuanSurat);

        List<PengajuanSuratModel> listPengajuan = new ArrayList<>();
        if(user.getRole().getNama().equals("Admin TU") || user.getRole().getNama().equals("Kepala Sekolah") ){
            listPengajuan = pengajuanSuratService.getListPengajuanSurat();
        }else {
            listPengajuan = user.getListPengajuanSurat();
        }
        model.addAttribute("username", user.getRole().getNama());
        model.addAttribute("listPengajuan", listPengajuan);

        model.addAttribute("hasil", hasilTambah);
        model.addAttribute("pengajuanSurat", pengajuanSurat);
        model.addAttribute("title", "Menambah Pengajuan Surat");

        return "daftar-pengajuan-surat";
    }

    //Menghapus pengajuan Surat yang ada pada daftar pengajuan Surat
    @RequestMapping(value="/pengajuan-surat/hapus/{username}/{id}")
    private String deletePengajuanSurat(@PathVariable String username,
                                        @PathVariable(value = "id") Integer idPengajuanSurat, Model model) {
        String hasilHapus = pengajuanSuratService.deletePengajuanSurat(idPengajuanSurat);

        UserModel user = userService.getUserByUsername(username);
        List<PengajuanSuratModel> listPengajuan = new ArrayList<>();
        if(user.getRole().getNama().equals("Admin TU") || user.getRole().getNama().equals("Kepala Sekolah") ){
            listPengajuan = pengajuanSuratService.getListPengajuanSurat();
        }else {
            listPengajuan = user.getListPengajuanSurat();
        }
        model.addAttribute("username", user.getRole().getNama());
        model.addAttribute("listPengajuan", listPengajuan);

        model.addAttribute("title", "Hapus Pengajuan Surat");
        model.addAttribute("hasil", hasilHapus);
        return "daftar-pengajuan-surat";
    }

    @RequestMapping(value = "pengajuan-surat/ubah/{username}/{id}", method = RequestMethod.GET)
    private String ubahPengajuanSuratFormPage(@PathVariable String username,
                                                @PathVariable(value = "id") Integer idPengajuanSurat, Model model) {
        UserModel user = userService.getUserByUsername(username);
        String statusUpdate = "";
        PengajuanSuratModel pengajuanSurat = pengajuanSuratService.getPengajuanSurat(idPengajuanSurat);
        if(pengajuanSurat.getStatus().equals("Menunggu Persetujuan") && user.getRole().getNama().equals("Kepala Sekolah")){
            model.addAttribute("username", "Kepala Sekolah");

        }else if(pengajuanSurat.getStatus().equals("Disetujui") && user.getRole().getNama().equals("Admin TU")){
            model.addAttribute("username", "Admin TU");
        }


        model.addAttribute("statusUpdate", pengajuanSurat.getStatusUpdate());
        model.addAttribute("username", user.getRole().getNama());
        model.addAttribute("id", idPengajuanSurat);
        model.addAttribute("pengajuanSurat", pengajuanSurat);
        model.addAttribute("title", "Ubah Pengajuan Surat");
        return "form-update-status-pengajuan-surat";
    }

    @RequestMapping(value = "pengajuan-surat/ubah/{username}/{id}", method = RequestMethod.POST)
    private String ubahPengajuanSuratSubmit(@PathVariable String username,
                                            @PathVariable(value = "id") Integer idPengajuanSurat,
                                            @ModelAttribute PengajuanSuratModel pengajuanSurat, Model model) {
//        PengajuanSuratModel pengajuanSurat = pengajuanSuratService.getPengajuanSurat(idPengajuanSurat);
        System.out.println(pengajuanSurat.getStatusUpdate());

        String hasilUbah = pengajuanSuratService.ubahPengajuanSurat(pengajuanSurat);
        System.out.println(hasilUbah);

        List<PengajuanSuratModel> listPengajuan = new ArrayList<>();
        listPengajuan = pengajuanSuratService.getListPengajuanSurat();

        UserModel user = userService.getUserByUsername(username);

        model.addAttribute("listPengajuan", listPengajuan);
        model.addAttribute("username", user.getRole().getNama());
        model.addAttribute("title", "Ubah Pengajuan Surat");
        model.addAttribute("hasil", hasilUbah);
        return "daftar-pengajuan-surat";
    }
}
