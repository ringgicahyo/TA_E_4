package apap.tugasakhir.situ.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "lowongan")
public class LowonganModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Size(max = 200)
    @Column(name = "judul", nullable = false, columnDefinition = "varchar(200)")
    private String judul;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyy")
    @Column(name="tanggalDibuka", nullable = false)
    private Date tanggalDibuka;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyy")
    @Column(name="tanggalDitutup", nullable = false)
    private Date tanggalDitutup;

    @NotNull
    @Size(max = 200)
    @Column(name="keterangan", nullable = false, columnDefinition = "varchar(200)")
    private String keterangan;

//    @ManyToOne
//    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private UserModel user;
//
//    @ManyToOne
//    @JoinColumn(name = "jenisLowonganId", referencedColumnName = "id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private JenisLowonganModel jenisLowongan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public Date getTanggalDibuka() {
        return tanggalDibuka;
    }

    public void setTanggalDibuka(Date tanggalDibuka) {
        this.tanggalDibuka = tanggalDibuka;
    }

    public Date getTanggalDitutup() {
        return tanggalDitutup;
    }

    public void setTanggalDitutup(Date tanggalDitutup) {
        this.tanggalDitutup = tanggalDitutup;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    @NotNull
    @Column(name="jumlah", nullable = false)
    private Integer jumlah;


}
