package apap.tugasakhir.situ.service;

import apap.tugasakhir.situ.rest.EmployeeDetailResponse;
import apap.tugasakhir.situ.rest.Setting;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserRestServiceImpl implements UserRestService {
    private final WebClient webClient;

    public UserRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.userProfileUrl).build();
    }

    @Override
    public Mono<EmployeeDetailResponse> getUserProfile(String uuid) {
        return this.webClient.get().uri("/api/employees/" + uuid).retrieve().bodyToMono(EmployeeDetailResponse.class);
    }
}
