package apap.tugasakhir.situ.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="pengajuanSurat")
public class PengajuanSuratModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name="tanggalPengajuan", nullable = false)
    private Date tanggalPengajuan;

    @Column(name="tanggalDisetujui", nullable = true)
    private Date tanggalDisetujui;

    @NotNull
    @Column(name="keterangan", nullable = false)
    private String keterangan;

    @NotNull
    @Column(name="status", nullable = false)
    private String status;

    @NotNull
    @Column(name="noSurat", nullable = false)
    private String noSurat;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "uuid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "jenisSuratId", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private JenisSuratModel jenisSurat;

    @Transient
    private Integer idJenisSurat;

    @Transient
    private String statusUpdate;

//    @Transient
//    private String uuid;

    @Transient
    private String username;

    @Transient
    private String password;

    public PengajuanSuratModel() {
    }

    public Date getTanggalPengajuan() {
        return tanggalPengajuan;
    }

    public void setTanggalPengajuan(Date tanggalPengajuan) {
        this.tanggalPengajuan = tanggalPengajuan;
    }

    public Date getTanggalDisetujui() {
        return tanggalDisetujui;
    }

    public void setTanggalDisetujui(Date tanggalDisetujui) {
        this.tanggalDisetujui = tanggalDisetujui;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNoSurat() {
        return noSurat;
    }

    public void setNoSurat(String noSurat) {
        this.noSurat = noSurat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Integer getIdJenisSurat() {
        return idJenisSurat;
    }

    public void setIdJenisSurat(Integer idJenisSurat) {
        this.idJenisSurat = idJenisSurat;
    }

    public JenisSuratModel getJenisSurat() {
        return jenisSurat;
    }

    public void setJenisSurat(JenisSuratModel jenisSurat) {
        this.jenisSurat = jenisSurat;
    }

    public String getStatusUpdate() {
        return statusUpdate;
    }

    public void setStatusUpdate(String statusUpdate) {
        this.statusUpdate = statusUpdate;
    }

//    public String getUuid() {return uuid; }
//
//    public void setUuid(String uuid) { this.uuid = uuid; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password;}

    public void setPassword(String password) { this.password = password; }
}
