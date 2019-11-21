package apap.tugasakhir.situ.service;

import apap.tugasakhir.situ.rest.AddEmployeeResponse;
import apap.tugasakhir.situ.rest.LoggedUserDetailResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface UserRestService {
    Mono<LoggedUserDetailResponse> getUserProfileForEmployee(String uuid);
    Mono<LoggedUserDetailResponse> getUserProfileForTeacher(String uuid);
    Mono<LoggedUserDetailResponse> getUserProfileForStudent(String uuid);
    Mono<AddEmployeeResponse> postAddEmployee(JSONObject data);
}
