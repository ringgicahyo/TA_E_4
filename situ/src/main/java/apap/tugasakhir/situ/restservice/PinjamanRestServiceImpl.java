package apap.tugasakhir.situ.restservice;

import org.springframework.web.reactive.function.client.WebClient;

import apap.tugasakhir.situ.rest.Setting;
import apap.tugasakhir.situ.rest.PinjamanDetail;

public class PinjamanRestServiceImpl {

  private final WebClient webClient;

  public PinjamanRestServiceImpl(WebClient.Builder webclientBuilder) {
    this.webClient = webclientBuilder.baseUrl(Setting.pengajuanPinjamanUrl).build();
  }

  public PinjamanDetail pengajuanPinjamanPost(PinjamanDetail pinjaman) {
    return this.webClient.post().uri("/arg0").bodyValue(pinjaman).retrieve().bodyToMono(PinjamanDetail.class).block();
  }
}