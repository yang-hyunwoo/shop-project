package shop.project.mall.domain.prdc;

import jakarta.persistence.*;
import lombok.Getter;
import shop.project.mall.domain.store.Store;

import java.util.Objects;

@Entity
@Getter
public class PrdtList {

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
