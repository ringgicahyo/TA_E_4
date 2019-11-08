package apap.tugasakhir.situ.controller;

import apap.tugasakhir.situ.model.LowonganModel;
import apap.tugasakhir.situ.service.LowonganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LowonganController {
    @Autowired
    private LowonganService lowonganService;

    @RequestMapping("/lowongan")
    public String viewListLowongan(Model model){
        List<LowonganModel> listLowongan = lowonganService.getLowonganList();
        model.addAttribute("listLowongan", listLowongan);
        return "view-all-lowongan";
    }

    @GetMapping(value = "/lowongan/tambah")
    public String addLowonganFormPage(Model model){
        LowonganModel newLowongan = new LowonganModel();
        model.addAttribute("lowongan", newLowongan);
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

    @GetMapping(value = "/lowongan/update/{id}")
    public String updateLowonganFormPage(@PathVariable Integer id, Model model){
        LowonganModel existingLowongan = lowonganService.getLowonganById(id).get();
        model.addAttribute("existingLowongan", existingLowongan);
        return "form-update-lowongan";
    }

    @PostMapping(value = "/lowongan/update/{id}")
    public String updateLowonganFormSubmit(@PathVariable Integer id, @ModelAttribute LowonganModel lowongan, Model model){
        LowonganModel newLowonganData = lowonganService.updateLowongan(lowongan);
        model.addAttribute("newLowonganData", newLowonganData);
        return "redirect:/lowongan/update/{id}";
    }
}
