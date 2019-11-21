package apap.tugasakhir.situ.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SiPerpusUserDetail {
    @JsonProperty("username")
    private String username;

    @JsonProperty("role")
    private SiPerpusUserRoleDetail role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SiPerpusUserRoleDetail getRole() {
        return role;
    }

    public void setRole(SiPerpusUserRoleDetail role) {
        this.role = role;
    }
}
