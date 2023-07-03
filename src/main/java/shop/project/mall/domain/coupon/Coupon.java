package shop.project.mall.domain.coupon;


import jakarta.persistence.*;
import lombok.Getter;
import shop.project.mall.domain.constant.CouponType;
import shop.project.mall.domain.prdc.PrdcCode;

import java.util.Objects;

@Entity
@Getter
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="couponId")
    private Long id;

    @Column(length = 1000)
    private String prodCodeId;

    @OneToOne(fetch = FetchType.LAZY)
    private PrdcCode prdcCode;

    @Column(length = 1000)
    private String couponName;

    @Enumerated(EnumType.STRING)
    private CouponType couponType;

    private Integer couponPercent;

    private Integer couponMoney;

    private boolean deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coupon that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
