package shop.project.mall.domain.coupon;


import jakarta.persistence.*;
import lombok.Getter;
import shop.project.mall.domain.user.User;

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

}
