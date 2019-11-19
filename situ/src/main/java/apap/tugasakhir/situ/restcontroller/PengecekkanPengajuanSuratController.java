package apap.tugasakhir.situ.restcontroller;

import apap.tugasakhir.situ.model.PengajuanSuratModel;
import apap.tugasakhir.situ.service.PengecekkanPengajuanSuratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1")
public class PengecekkanPengajuanSuratController {
    @Autowired
    private PengecekkanPengajuanSuratService pengecekkanPengajuanSuratService;

    @GetMapping(value="/pengajuan-surat/{noSurat}")
    private PengajuanSuratModel retrievePengajuanSurat(@PathVariable("noSurat") String noSurat) {
        try {
            return pengecekkanPengajuanSuratService.findPengajuanSurat(noSurat);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Nomor Surat " + String.valueOf(noSurat) + " Not Found!");
        }
    }

}
