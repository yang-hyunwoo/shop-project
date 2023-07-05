package shop.project.mall.domain.prdc;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.project.mall.domain.coupon.CouponRegHist;
import shop.project.mall.domain.user.User;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Entity
public class PrdtPurList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="prdtPurListId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "prdtListId")
    private PrdtList prdtList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "couponRegHist")
    private CouponRegHist couponRegHist;

    @Column(nullable = false , length = 1000)
    private String impUid;

    @Column(nullable = false , length = 1000)
    private String merchantUid;

    @Column(length = 50)
    private String payName;

    @Column(length = 50)
    private String payEmail;

    @Column(length = 1000)
    private String failReason;

    @Column(length = 1000)
    private String refundReason;

    private int realPaid;

    private int salePercent;

    private int salePaid;

    private int prdtCnt;

    private int point;

    @Column(length = 50)
    private String deliveryAddr;

    @Column(length = 50)
    private String deliveryTel;

    @Column(length = 40)
    private String deliveryMsg;

    @Column(length = 30)
    private String deliveryName;

    private boolean deleted;
    @Builder
    public PrdtPurList(PrdtList prdtList,
                       User user,
                       CouponRegHist couponRegHist,
                       String impUid,
                       String merchantUid,
                       String payName,
                       String payEmail,
                       String failReason,
                       String refundReason,
                       int realPaid,
                       int salePercent,
                       int salePaid,
                       int prdtCnt,
                       int point,
                       String deliveryAddr,
                       String deliveryTel,
                       String deliveryMsg,
                       String deliveryName,
                       boolean deleted) {
        this.prdtList = prdtList;
        this.user = user;
        this.couponRegHist = couponRegHist;
        this.impUid = impUid;
        this.merchantUid = merchantUid;
        this.payName = payName;
        this.payEmail = payEmail;
        this.failReason = failReason;
        this.refundReason = refundReason;
        this.realPaid = realPaid;
        this.salePercent = salePercent;
        this.salePaid = salePaid;
        this.prdtCnt = prdtCnt;
        this.point = point;
        this.deliveryAddr = deliveryAddr;
        this.deliveryTel = deliveryTel;
        this.deliveryMsg = deliveryMsg;
        this.deliveryName = deliveryName;
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrdtPurList that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
