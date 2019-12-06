package apap.tugasakhir.situ.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.reactive.function.client.WebClientResponseException;

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
    try {      
      PinjamanDetail postPinjaman = pinjamanRestService.pengajuanPinjamanPost(pinjamanDetail);      
      redirectAttributes.addFlashAttribute("message", "Pinjaman berhasil ditambahkan dengan jumlah pinjaman " + postPinjaman.getJumlahPinjaman() + " atas ID Anggota " + postPinjaman.getIdAnggota());
    } catch(WebClientResponseException.NotFound error) {      
      redirectAttributes.addFlashAttribute("message2", "Mohon maaf. Telah terjadi error 404 (ID Anggota tidak dapat ditemukan).");      
    } catch(WebClientResponseException.BadRequest error) {      
      redirectAttributes.addFlashAttribute("message2", "Mohon maaf. Telah terjadi error 400 (Bad Request).");      
    } catch(WebClientResponseException.InternalServerError error) {
      redirectAttributes.addFlashAttribute("message2", "Mohon maaf. Telah terjadi error 500 (Internal Server Error).");      
    }
    return "redirect:/pengajuan-pinjaman";
  }
}