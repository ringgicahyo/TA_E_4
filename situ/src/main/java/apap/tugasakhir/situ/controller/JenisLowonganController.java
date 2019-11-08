package apap.tugasakhir.situ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

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
}

// @Controller
// public class JenisSuratController {

//   @Autowired
//   JenisSuratService jenisSuratService;

//   @RequestMapping(value = "/jenis-surat/add", method = RequestMethod.GET)
//   public String addJenisSuratForm(Model model) {
//     JenisSuratModel jenisSurat = new JenisSuratModel();
//     model.addAttribute("jenisSurat", jenisSurat);

//     return "add-jenis-surat-form";
//   }

//   @RequestMapping(value = "/jenis-surat/add", method = RequestMethod.POST)
//   public String addJenisSuratSubmit(@ModelAttribute JenisSuratModel jenisSurat, Model model, RedirectAttributes redirectAttributes){
//     boolean addStatus = jenisSuratService.addJenisSurat(jenisSurat);

//     //Jika gagal add
//     if(!addStatus) {
//       redirectAttributes.addFlashAttribute("message", "Maaf gagal menambahkan jenis surat dengan nama " + jenisSurat.getNama() + ". Mohon masukkan nama yang berbeda!");
//       redirectAttributes.addFlashAttribute("status", false);
//       return "redirect:/jenis-surat/add";
//     }

//     //Jika berhasil add
//     redirectAttributes.addFlashAttribute("status", true);    
//     redirectAttributes.addFlashAttribute("message", "Penambahan jenis surat dengan nama " + jenisSurat.getNama() + " berhasil!");      

//     return "redirect:/jenis-surat/add";
//   }
// }