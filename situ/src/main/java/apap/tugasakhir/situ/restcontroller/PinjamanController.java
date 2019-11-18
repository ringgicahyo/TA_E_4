package apap.tugasakhir.situ.restcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

@Controller
public class PinjamanController {

  @RequestMapping(value = "/pengajuan-pinjaman", method = RequestMethod.GET) 
  public String pengajuanPinjamanFormPage(Model model) {
    return "pengajuan-pinjaman-form";
  }
}