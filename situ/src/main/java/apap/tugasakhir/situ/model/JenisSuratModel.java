package apap.tugasakhir.situ.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="jenisSurat")
public class JenisSuratModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<PengajuanSuratModel> listPengajuanSurat;

}
