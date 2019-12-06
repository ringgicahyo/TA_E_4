package apap.tugasakhir.situ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import apap.tugasakhir.situ.model.JenisLowonganModel;
import apap.tugasakhir.situ.service.JenisLowonganService;

@Controller
public class JenisLowonganController {
  @Autowired
  JenisLowonganService jenisLowonganService;

  @RequestMapping(value = "/jenis-lowongan/add", method = RequestMethod.GET)
  public String addJenisLowonganForm(Model model) {
    JenisLowonganModel jenisLowongan = new JenisLowonganModel();
    model.addAttribute("jenisLowongan", jenisLowongan);

    return "add-jenis-lowongan-form";
  }

  @RequestMapping(value = "/jenis-lowongan/add", method = RequestMethod.POST)
  public String addJenisLowonganSubmit(@ModelAttribute JenisLowonganModel jenisLowongan, RedirectAttributes redirectAttributes) {
    boolean addStatus = jenisLowonganService.addJenisLowongan(jenisLowongan);

    //Jika gagal add
    if(!addStatus) {
      redirectAttributes.addFlashAttribute("message", "Maaf gagal menambahkan jenis lowongan dengan nama " + jenisLowongan.getNama() + ". Mohon masukkan nama yang berbeda!");
      redirectAttributes.addFlashAttribute("status", false);
      return "redirect:/jenis-lowongan/add";
    }

    //Jika berhasil add
    redirectAttributes.addFlashAttribute("status", true);    
    redirectAttributes.addFlashAttribute("message", "Penambahan jenis lowongan dengan nama " + jenisLowongan.getNama() + " berhasil!");      
    
    return "redirect:/jenis-lowongan/add";
  }

  @RequestMapping(value = "/jenis-lowongan/view-all", method = RequestMethod.GET)
  public String viewAllJenisLowongan(Model model) {
    model.addAttribute("jenisLowonganList", jenisLowonganService.getAllJenisLowongan());
    return "view-all-jenis-lowongan";
  }

  @RequestMapping(value = "/jenis-lowongan/delete/{idJenisLowongan}", method = RequestMethod.POST)
  public String viewAllJenisSurat(@PathVariable Integer idJenisLowongan, Model model, RedirectAttributes redirectAttributes) {     
    JenisLowonganModel jenisLowongan = jenisLowonganService.getJenisLowongan(idJenisLowongan);
    jenisLowonganService.deleteJenisLowongan(jenisLowongan);

    redirectAttributes.addFlashAttribute("status", true);
    redirectAttributes.addFlashAttribute("message", "Jenis lowongan dengan nama " + jenisLowongan.getNama() + " berhasil dihapus.");    
    return "redirect:/jenis-lowongan/view-all";
  }
}