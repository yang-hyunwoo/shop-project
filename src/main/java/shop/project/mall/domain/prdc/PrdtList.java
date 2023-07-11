package shop.project.mall.domain.prdc;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.project.mall.domain.common.AuditingFields;
import shop.project.mall.domain.store.Store;

import java.util.Objects;
@NoArgsConstructor
@Getter
@Entity
public class PrdtList extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="prdtListId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "storeId")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "prdtReviewId")
    private PrdtReview prdtReview;

    @OneToOne(fetch = FetchType.LAZY)
    private PrdcCode prdcCode;

    @Column(length = 500)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private int realPaid;

    private int salePercent;

    private int salePaid;

    private int prdtCnt;

    private int deliveryMoney;

    private int prdtMaxCnt;

    private int prdtCntBuy;

    private boolean deleted;

    @Builder
    public PrdtList(Store store,
                    PrdtReview prdtReview,
                    PrdcCode prdcCode,
                    String title,
                    String content,
                    int realPaid,
                    int salePercent,
                    int salePaid,
                    int prdtCnt,
                    int deliveryMoney,
                    int prdtMaxCnt,
                    int prdtCntBuy,
                    boolean deleted) {
        this.store = store;
        this.prdtReview = prdtReview;
        this.prdcCode = prdcCode;
        this.title = title;
        this.content = content;
        this.realPaid = realPaid;
        this.salePercent = salePercent;
        this.salePaid = salePaid;
        this.prdtCnt = prdtCnt;
        this.deliveryMoney = deliveryMoney;
        this.prdtMaxCnt = prdtMaxCnt;
        this.prdtCntBuy = prdtCntBuy;
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrdtList that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
