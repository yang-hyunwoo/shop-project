package shop.project.mall.domain.point;

import jakarta.persistence.*;
import lombok.Getter;
import shop.project.mall.domain.user.User;

import java.time.LocalDateTime;

@Entity
@Getter
public class PointHist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pointHistId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pointCodeId")
    private PointCode pointCode;

    private int pointMoney;

    private boolean useDtls;

    private LocalDateTime regAt;

}
