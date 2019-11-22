package apap.tugasakhir.situ.service;

import apap.tugasakhir.situ.rest.AddEmployeeResponse;
import apap.tugasakhir.situ.rest.LoggedUserDetailResponse;
import apap.tugasakhir.situ.rest.Setting;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Random;

@Service
public class UserRestServiceImpl implements UserRestService {
    private final WebClient webClient;

    public UserRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.userProfileUrl).build();
    }

    @Override
    public Mono<LoggedUserDetailResponse> getUserProfileForEmployee(String uuid) {
        return this.webClient.get().uri("/api/employees/" + uuid).retrieve().bodyToMono(LoggedUserDetailResponse.class);
    }

    @Override
    public Mono<LoggedUserDetailResponse> getUserProfileForTeacher(String uuid) {
        return this.webClient.get().uri("/api/teachers/" + uuid).retrieve().bodyToMono(LoggedUserDetailResponse.class);
    }

    @Override
    public Mono<LoggedUserDetailResponse> getUserProfileForStudent(String uuid) {
        return this.webClient.get().uri("/api/students/" + uuid).retrieve().bodyToMono(LoggedUserDetailResponse.class);
    }

    @Override
    public Mono<AddEmployeeResponse> postAddEmployee(JSONObject data) {
        // Generate NIP here.
        String tanggalLahir = data.getString("tanggalLahir");
        String idUser = data.getString("idUser");
        String nip = generateNip(tanggalLahir, idUser);

        // Add NIP.
        data.put("nip", nip);

        System.out.println(data.toString());

        return this.webClient.post().uri("http://sivitas.herokuapp.com/api/employees")
                .header("Content-Type", "application/json")
                .bodyValue(data.toString())
                .retrieve()
                .bodyToMono(AddEmployeeResponse.class);
    }

    private String generateNip(String tanggalLahir, String uuid) {
        String nip = "";

        nip += "P";

        nip += tanggalLahir.substring(8, 10);
        nip += tanggalLahir.substring(5, 7);
        nip += tanggalLahir.substring(0, 4);

        // Generate random capital letters.
        Random r = new Random();
        char randomLetter1 = (char) ((int)'A' + r.nextInt(26));
        char randomLetter2 = (char) ((int)'A' + r.nextInt(26));
        nip += randomLetter1;
        nip += randomLetter2;

        // Generate random numbers.
        int randomNumber = r.nextInt(1000);
        nip += randomNumber;

        nip += uuid;

        return nip;
    }

}
