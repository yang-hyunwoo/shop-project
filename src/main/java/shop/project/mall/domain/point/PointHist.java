package shop.project.mall.domain.point;

import jakarta.persistence.*;
import lombok.Getter;
import shop.project.mall.domain.user.User;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PointHist that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
