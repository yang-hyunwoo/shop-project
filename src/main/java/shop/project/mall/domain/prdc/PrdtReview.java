package shop.project.mall.domain.prdc;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.project.mall.domain.user.User;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Entity
public class PrdtReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="prdtReviewId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "prdtPurListId")
    private PrdtPurList prdtPurList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId")
    private User user;

    @Column(length = 100)
    private String prdtReviewTitle;

    @Column(length = 4000)
    private String prdtReviewContent;

    private double reviewScore;

    private boolean deleted;
    @Builder
    public PrdtReview(PrdtPurList prdtPurList,
                      User user,
                      String prdtReviewTitle,
                      String prdtReviewContent,
                      double reviewScore,
                      boolean deleted) {
        this.prdtPurList = prdtPurList;
        this.user = user;
        this.prdtReviewTitle = prdtReviewTitle;
        this.prdtReviewContent = prdtReviewContent;
        this.reviewScore = reviewScore;
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrdtReview that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
