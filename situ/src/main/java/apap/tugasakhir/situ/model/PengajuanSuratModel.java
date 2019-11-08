package apap.tugasakhir.situ.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="pengajuanSurat")
public class PengajuanSuratModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel user;

    @Transient
    private Integer idJenisSurat;

    @ManyToOne
    @JoinColumn(name = "jenisSuratId", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private JenisSuratModel jenisSurat;

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
}
