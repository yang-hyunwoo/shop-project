package shop.project.mall.domain.point;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class PointCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pointCodeId")
    private Long id;

    @Column(nullable = false)
    private String pointCode;

    @Column(nullable = false)
    private String pointCodeName;


}
