package apap.tugasakhir.situ.restcontroller;

import apap.tugasakhir.situ.model.JenisSuratModel;
import apap.tugasakhir.situ.model.PengajuanSuratModel;
import apap.tugasakhir.situ.model.UserModel;
import apap.tugasakhir.situ.service.JenisSuratService;
import apap.tugasakhir.situ.restservice.PengecekkanPengajuanSuratService;
import apap.tugasakhir.situ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("api/v1")
public class PengecekkanPengajuanSuratController {
    @Autowired
    private PengecekkanPengajuanSuratService pengecekkanPengajuanSuratService;
    @Autowired
    private UserService userService;
    @Autowired
    private JenisSuratService jenisSuratService;

    @GetMapping(value="/pengajuan-surat/{noSurat}")
    private Map<String, Object> retrievePengajuanSurat(@PathVariable("noSurat") String noSurat) {
        try {
            PengajuanSuratModel result = pengecekkanPengajuanSuratService.findPengajuanSurat(noSurat);

            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("Jenis Surat", result.getJenisSurat().getNama());
            resultMap.put("Keterangan",  result.getKeterangan());
            resultMap.put("Status", result.getStatus());
            resultMap.put("User ID",  result.getUser().getUuid());
            return resultMap;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Nomor Surat " + String.valueOf(noSurat) + " Not Found!");
        }
    }

    @PostMapping(value = "/pengajuan-surat/add")
    private String createPengajuanSurat(@Valid @RequestBody PengajuanSuratModel pengajuanSurat,
                                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else {
            JenisSuratModel jenisSurat = jenisSuratService.getJenisSurat(pengajuanSurat.getIdJenisSurat());
            pengajuanSurat.setJenisSurat(jenisSurat);

            UserModel user = userService.getUserByUsername(pengajuanSurat.getUsernameUser());
            pengajuanSurat.setUser(user);
            pengajuanSurat.setStatus("Menunggu Persetujuan");

            PengajuanSuratModel hasil = pengecekkanPengajuanSuratService.createPengajuanSurat(pengajuanSurat);
            if(hasil.getStatus().equals("Menunggu Persetujuan")) {
                redirectAttributes.addFlashAttribute("message", "Berhasil");
            }
            else {
                redirectAttributes.addFlashAttribute("message", "Gagal");
            }
            return "redirect:/pengajuan-pinjaman";
        }
    }

}
