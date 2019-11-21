package apap.tugasakhir.situ.controller;

import apap.tugasakhir.situ.model.LowonganModel;
import apap.tugasakhir.situ.model.UserModel;
import apap.tugasakhir.situ.service.LowonganService;
import apap.tugasakhir.situ.model.JenisLowonganModel;
import apap.tugasakhir.situ.service.JenisLowonganService;
import apap.tugasakhir.situ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class LowonganController {
    @Autowired
    private LowonganService lowonganService;

    @Autowired
    JenisLowonganService jenisLowonganService;

    @Autowired
    UserService userService;

    @RequestMapping("/lowongan")
    public String viewListLowongan(Model model){
        List<LowonganModel> listLowongan = lowonganService.getLowonganList();
        model.addAttribute("listLowongan", listLowongan);
        return "view-all-lowongan";
    }

    @GetMapping(value = "/lowongan/tambah")
    public String addLowonganFormPage(Model model){
        LowonganModel newLowongan = new LowonganModel();
        List<JenisLowonganModel> listJenisLowongan = jenisLowonganService.getAllJenisLowongan();
        model.addAttribute("lowongan", newLowongan);
        model.addAttribute("listJenisLowongan", listJenisLowongan);
        return "form-add-lowongan";
    }

    @PostMapping(value = "/lowongan/tambah/{username}")
    public String addLowonganFormSubmit(@PathVariable(value = "username") String username, @ModelAttribute LowonganModel lowongan, Model model, RedirectAttributes redirectAttributes){
        UserModel user = userService.getUserByUsername(username);
        lowongan.setUser(user);
        lowonganService.addLowongan(lowongan);
        redirectAttributes.addFlashAttribute("status", true);
        redirectAttributes.addFlashAttribute("message", "BERHASIL! Penambahan lowongan dengan judul " + lowongan.getJudul() + " berhasil!");
        model.addAttribute("judulLowongan", lowongan.getJudul());
        return "redirect:/lowongan/tambah";
    }

    @GetMapping(value = "/lowongan/view/{id}")
    public String viewLowonganById(@PathVariable Integer id, Model model){
        LowonganModel lowongan = lowonganService.getLowonganById(id).get();
        model.addAttribute("lowongan", lowongan);
        return "view-lowongan-by-id";
    }

    @GetMapping(value = "/lowongan/update/{id}")
    public String updateLowonganFormPage(@PathVariable Integer id, Model model){
        LowonganModel existingLowongan = lowonganService.getLowonganById(id).get();
        List<JenisLowonganModel> listJenisLowongan = jenisLowonganService.getAllJenisLowongan();
        model.addAttribute("lowongan", existingLowongan);
        model.addAttribute("listJenisLowongan", listJenisLowongan);
        return "form-update-lowongan";
    }

    @PostMapping(value = "/lowongan/update/{id}")
    public String updateLowonganFormSubmit(@PathVariable Integer id, @ModelAttribute LowonganModel lowongan, Model model, RedirectAttributes redirectAttributes){
        LowonganModel existingLowongan = lowonganService.getLowonganById(id).get();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if(formatter.format(existingLowongan.getTanggalDibuka()).compareTo(formatter.format(new Date())) > 0){
            LowonganModel newLowonganData = lowonganService.updateLowongan(lowongan);
            model.addAttribute("newLowonganData", newLowonganData);
            redirectAttributes.addFlashAttribute("successStatus", true);
            redirectAttributes.addFlashAttribute("successMessage", "BERHASIL! Lowongan dengan judul " + newLowonganData.getJudul() + " berhasil diubah!");
        } else {
            redirectAttributes.addFlashAttribute("failureStatus", true);
            redirectAttributes.addFlashAttribute("failureMessage", "GAGAL! Pengubahan lowongan telah melewati batas waktunya. Batas maksimal pengubahan adalah satu hari sebelum tanggal lowongan dibuka.");
        }
        return "redirect:/lowongan/update/{id}";
    }

    @RequestMapping(value = "/lowongan/delete/{id}")
    public String deleteLowongan(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes redirectAttributes){
        LowonganModel lowongan = lowonganService.getLowonganById(id).get();
        lowonganService.deleteLowongan(lowongan);
        model.addAttribute("lowongan", lowongan);
        redirectAttributes.addFlashAttribute("deleteStatus", true);
        redirectAttributes.addFlashAttribute("deleteMessage", "BERHASIL! Lowongan dengan judul " + lowongan.getJudul() + " berhasil dihapus!");
        return "redirect:/lowongan";
    }
}
