package shop.project.mall.domain.prdc;

import jakarta.persistence.*;
import lombok.Getter;
import shop.project.mall.domain.user.User;

@Entity
@Getter
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

}
