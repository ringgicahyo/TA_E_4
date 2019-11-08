package apap.tugasakhir.situ.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<PengajuanSuratModel> listPengajuanSurat;

}
