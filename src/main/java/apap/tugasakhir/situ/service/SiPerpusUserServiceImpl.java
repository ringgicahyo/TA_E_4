package apap.tugasakhir.situ.service;

import apap.tugasakhir.situ.rest.Setting;
import apap.tugasakhir.situ.rest.SiPerpusUserDetailResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SiPerpusUserServiceImpl implements SiPerpusUserService{
    private final WebClient webClient;

    public SiPerpusUserServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.siPerpusUrl).build();
    }

    @Override
    public Mono<SiPerpusUserDetailResponse> getSiPerpusUser() {
        return this.webClient.get().uri("/api/users").retrieve().bodyToMono(SiPerpusUserDetailResponse.class);
    }
}
