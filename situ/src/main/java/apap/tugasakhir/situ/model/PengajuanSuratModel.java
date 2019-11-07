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
    private Long idPengajuanSurat;

    @NotNull
//    @Size(max = 1)
    @Column(name="tanggalPengajuan", nullable = false)
    private Date tanggalPengajuan;

    @NotNull
//    @Size(max = 1)
    @Column(name="tanggalDisetujui", nullable = false)
    private Date tanggalDisetujui;

    @NotNull
//    @Size(max = 1)
    @Column(name="keterangan", nullable = false)
    private String keterangan;

    @NotNull
//    @Size(max = 1)
    @Column(name="status", nullable = false)
    private String status;

    @NotNull
//    @Size(max = 1)
    @Column(name="noSurat", nullable = false)
    private String noSurat;


    @ManyToOne
    @JoinColumn(name = "pengajuanSuratId", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private JenisSuratModel jenisSurat;


    public PengajuanSuratModel() {
    }

    public Long getIdPengajuanSurat() {
        return idPengajuanSurat;
    }

    public void setIdPengajuanSurat(Long idPengajuanSurat) {
        this.idPengajuanSurat = idPengajuanSurat;
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
}
