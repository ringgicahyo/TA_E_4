package apap.tugasakhir.situ.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SiPerpusUserDetailResponse {
    @JsonProperty("status")
    private Integer status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private List<SiPerpusUserDetail> result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SiPerpusUserDetail> getResult() {
        return result;
    }

    public void setResult(List<SiPerpusUserDetail> result) {
        this.result = result;
    }
}
