package shop.project.mall.domain.prdc;

import jakarta.persistence.*;
import lombok.Getter;
import shop.project.mall.domain.coupon.CouponRegHist;
import shop.project.mall.domain.user.User;

@Entity
@Getter
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

}
