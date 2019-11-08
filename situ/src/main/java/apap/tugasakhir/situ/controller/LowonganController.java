package apap.tugasakhir.situ.controller;

import apap.tugasakhir.situ.model.LowonganModel;
import apap.tugasakhir.situ.service.LowonganService;
import apap.tugasakhir.situ.model.JenisLowonganModel;
import apap.tugasakhir.situ.service.JenisLowonganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LowonganController {
    @Autowired
    private LowonganService lowonganService;

    @Autowired
    JenisLowonganService jenisLowonganService;

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

    @PostMapping(value = "/lowongan/tambah")
    public String addLowonganFormSubmit(@ModelAttribute LowonganModel lowongan, Model model){
        lowonganService.addLowongan(lowongan);
        model.addAttribute("judulLowongan", lowongan.getJudul());
        return "redirect:/lowongan/tambah";
    }

    @GetMapping(value = "/lowongan/view/{id}")
    public String viewLowonganById(@PathVariable Integer id, Model model){
        LowonganModel lowongan = lowonganService.getLowonganById(id).get();
        model.addAttribute("lowongan", lowongan);
        return "view-lowongan-by-id";
    }
}
