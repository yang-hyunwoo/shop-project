package shop.project.mall.domain.coupon;


import jakarta.persistence.*;
import lombok.Getter;
import shop.project.mall.domain.user.User;

import java.util.Objects;

@Entity
@Getter
public class CouponRegHist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="couponRegHistId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "couponId")
    private Coupon coupon;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId")
    private User user;

    private boolean useYn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CouponRegHist that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
