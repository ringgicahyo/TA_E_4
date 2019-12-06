package apap.tugasakhir.situ.restservice;

import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import org.springframework.web.reactive.function.client.WebClient;
import org.json.JSONObject;

import apap.tugasakhir.situ.rest.Setting;
import apap.tugasakhir.situ.rest.PinjamanDetail;

@Service
public class PinjamanRestServiceImpl implements PinjamanRestService{

  private final WebClient webClient;

  public PinjamanRestServiceImpl(WebClient.Builder webclientBuilder) {
    this.webClient = webclientBuilder.baseUrl(Setting.pengajuanPinjamanUrl).build();
  }

  public PinjamanDetail pengajuanPinjamanPost(PinjamanDetail pinjaman) {
    JSONObject data = new JSONObject();    
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String date = formatter.format(pinjaman.getTanggalPengajuan());

    data.put("idAnggota", pinjaman.getIdAnggota().toString());    
    data.put("tanggalPengajuan", date);    
    data.put("jumlahPinjaman", pinjaman.getJumlahPinjaman().toString());

    return this.webClient.post().uri("/api/pinjaman/ajukan").header("Content-Type", "application/json").bodyValue(data.toString()).retrieve().bodyToMono(PinjamanDetail.class).block();
  }
}