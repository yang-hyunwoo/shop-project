package shop.project.mall.domain.prdc;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class PrdcCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="prdcCodeId")
    private Long id;

    @Column(length = 50)
    private String prdcName;

    private Long prdcCode;

    private boolean deleted;


}
