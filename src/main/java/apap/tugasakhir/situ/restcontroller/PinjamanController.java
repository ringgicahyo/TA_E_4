package apap.tugasakhir.situ.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import apap.tugasakhir.situ.rest.PinjamanDetail;
import apap.tugasakhir.situ.restservice.PinjamanRestService;

import org.springframework.ui.Model;

@Controller
public class PinjamanController {

  @Autowired
  PinjamanRestService pinjamanRestService;

  @RequestMapping(value = "/pengajuan-pinjaman", method = RequestMethod.GET) 
  public String pengajuanPinjamanFormPage(Model model) {
    PinjamanDetail pinjamanDetail = new PinjamanDetail();
    model.addAttribute("pinjamanDetail", pinjamanDetail);
    return "pengajuan-pinjaman-form";
  }

  @RequestMapping(value = "/pengajuan-pinjaman", method = RequestMethod.POST)
  public String pengajuanPinjamanSubmit(@ModelAttribute PinjamanDetail pinjamanDetail, Model model, RedirectAttributes redirectAttributes) {    
    PinjamanDetail postPinjaman = pinjamanRestService.pengajuanPinjamanPost(pinjamanDetail);

    if(postPinjaman.getStatus() == 400) {
      redirectAttributes.addFlashAttribute("message", "Mohon maaf. Telah terjadi error.");
    }
    else {
      redirectAttributes.addFlashAttribute("message", "Pinjaman berhasil ditambahkan!");
    }    
    return "redirect:/pengajuan-pinjaman";
  }
}