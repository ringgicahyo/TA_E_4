package apap.tugasakhir.situ.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
// import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.GenerationType;
// import javax.persistence.FetchType;
// import javax.persistence.CascadeType;

// import java.util.List;


@Entity
@Table(name = "jenisSurat")
public class JenisSuratModel implements Serializable {

  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  @Size(max = 200)
  @Column(name = "nama", nullable = false, unique = true)
  private String nama;

  @NotNull
  @Size(max = 200)
  @Column(name = "keterangan", nullable = false)
  private String keterangan;

  // @OneToMany(mappedBy = "jenisSurat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  // private List<PengajuanSuratModel> listPengajuanSurat;

  /**
   * @param id the id to set
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @param nama the nama to set
   */
  public void setNama(String nama) {
    this.nama = nama;
  }

  /**
   * @param keterangan the keterangan to set
   */
  public void setKeterangan(String keterangan) {
    this.keterangan = keterangan;
  }

  // /**
  //  * @param listPengajuanSurat the listPengajuanSurat to set
  //  */
  // public void setListPengajuanSurat(List<PengajuanSuratModel> listPengajuanSurat) {
  //   this.listPengajuanSurat = listPengajuanSurat;
  // }

  /**
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * @return the nama
   */
  public String getNama() {
    return nama;
  }

  /**
   * @return the keterangan
   */
  public String getKeterangan() {
    return keterangan;
  }

  // /**
  //  * @return the listPengajuanSurat
  //  */
  // public List<PengajuanSuratModel> getListPengajuanSurat() {
  //   return listPengajuanSurat;
  // }

}