package apap.tugasakhir.situ.restservice;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import apap.tugasakhir.situ.rest.Setting;
import apap.tugasakhir.situ.rest.PinjamanDetail;

@Service
public class PinjamanRestServiceImpl implements PinjamanRestService{

  private final WebClient webClient;

  public PinjamanRestServiceImpl(WebClient.Builder webclientBuilder) {
    this.webClient = webclientBuilder.baseUrl(Setting.pengajuanPinjamanUrl).build();
  }

  public PinjamanDetail pengajuanPinjamanPost(PinjamanDetail pinjaman) {
    // MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
    // data.add("idAnggota", pinjaman.getIdAnggota().toString());    
    // data.add("tanggalPengajuan", pinjaman.getTanggalPengajuan().toString());    
    // data.add("jumlahPinjaman", pinjaman.getJumlahPinjaman().toString());
    return this.webClient.post().uri("pinjaman/add-pinjaman").bodyValue(pinjaman).retrieve().bodyToMono(PinjamanDetail.class).block();
  }
}