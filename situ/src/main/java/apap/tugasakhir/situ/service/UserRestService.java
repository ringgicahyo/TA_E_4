package apap.tugasakhir.situ.service;

import apap.tugasakhir.situ.rest.EmployeeDetailResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface UserRestService {
    Mono<EmployeeDetailResponse> getUserProfile(String uuid);

}
