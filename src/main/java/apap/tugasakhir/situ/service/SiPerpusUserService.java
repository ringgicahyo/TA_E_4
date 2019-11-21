package apap.tugasakhir.situ.service;

import apap.tugasakhir.situ.rest.SiPerpusUserDetailResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface SiPerpusUserService {
    Mono<SiPerpusUserDetailResponse> getSiPerpusUser();
}
