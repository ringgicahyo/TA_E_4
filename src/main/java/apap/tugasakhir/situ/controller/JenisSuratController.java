package apap.tugasakhir.situ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import  org.springframework.web.bind.annotation.PathVariable;

import apap.tugasakhir.situ.model.JenisSuratModel;
import apap.tugasakhir.situ.service.JenisSuratService;

@Controller
public class JenisSuratController {

  @Autowired
  JenisSuratService jenisSuratService;

  @RequestMapping(value = "/jenis-surat/add", method = RequestMethod.GET)
  public String addJenisSuratForm(Model model) {
    JenisSuratModel jenisSurat = new JenisSuratModel();
    model.addAttribute("jenisSurat", jenisSurat);

    return "add-jenis-surat-form";
  }

  @RequestMapping(value = "/jenis-surat/add", method = RequestMethod.POST)
  public String addJenisSuratSubmit(@ModelAttribute JenisSuratModel jenisSurat, Model model, RedirectAttributes redirectAttributes){
    boolean addStatus = jenisSuratService.addJenisSurat(jenisSurat);

    //Jika gagal add
    if(!addStatus) {
      redirectAttributes.addFlashAttribute("message", "Maaf gagal menambahkan jenis surat dengan nama " + jenisSurat.getNama() + ". Mohon masukkan nama yang berbeda!");
      redirectAttributes.addFlashAttribute("status", false);
      return "redirect:/jenis-surat/add";
    }

    //Jika berhasil add
    redirectAttributes.addFlashAttribute("status", true);    
    redirectAttributes.addFlashAttribute("message", "Penambahan jenis surat dengan nama " + jenisSurat.getNama() + " berhasil!");      

    return "redirect:/jenis-surat/add";
  }

  @RequestMapping(value = "/jenis-surat/view-all", method = RequestMethod.GET)
  public String viewAllJenisSurat(Model model) {
    model.addAttribute("jenisSuratList", jenisSuratService.getListJenisSurat());
    return "view-all-jenis-surat";
  }

  @RequestMapping(value = "/jenis-surat/delete/{idJenisSurat}", method = RequestMethod.POST)
  public String viewAllJenisSurat(@PathVariable Integer idJenisSurat, Model model, RedirectAttributes redirectAttributes) {     
    JenisSuratModel jenisSurat = jenisSuratService.getJenisSurat(idJenisSurat);
    jenisSuratService.deleteJenisSurat(jenisSurat);
    redirectAttributes.addFlashAttribute("status", true);
    redirectAttributes.addFlashAttribute("message", "Jenis surat dengan nama " + jenisSurat.getNama() + " berhasil dihapus.");    
    return "redirect:/jenis-surat/view-all";
  }

}