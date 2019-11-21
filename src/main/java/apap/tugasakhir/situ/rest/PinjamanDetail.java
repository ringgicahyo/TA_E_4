package apap.tugasakhir.situ.rest;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PinjamanDetail {

  @JsonProperty("status")
  private int status;
  
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @JsonProperty("tanggalPengajuan")
  private Date tanggalPengajuan;  

  @JsonProperty("jumlahPinjaman")
  private Integer jumlahPinjaman;

  @JsonProperty("idAnggota")
  private Integer idAnggota;

  /**
   * @param status the status to set
   */
  public void setStatus(int status) {
    this.status = status;
  }

  /**
   * @param tanggalPengajuan the tanggalPengajuan to set
   */
  public void setTanggalPengajuan(Date tanggalPengajuan) {
    this.tanggalPengajuan = tanggalPengajuan;
  }

  /**
   * @param jumlahPinjaman the jumlahPinjaman to set
   */
  public void setJumlahPinjaman(Integer jumlahPinjaman) {
    this.jumlahPinjaman = jumlahPinjaman;
  }

  /**
   * @param idAnggota the idAnggota to set
   */
  public void setIdAnggota(Integer idAnggota) {
    this.idAnggota = idAnggota;
  }

  /**
   * @return the tanggalPengajuan
   */
  public Date getTanggalPengajuan() {
    return tanggalPengajuan;
  }

  /**
   * @return the jumlahPinjaman
   */
  public Integer getJumlahPinjaman() {
    return jumlahPinjaman;
  }

  /**
   * @return the idAnggota
   */
  public Integer getIdAnggota() {
    return idAnggota;
  }

  /**
   * @return the status
   */
  public int getStatus() {
    return status;
  }
}