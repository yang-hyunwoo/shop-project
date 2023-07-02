package shop.project.mall.domain.article;

import jakarta.persistence.*;
import lombok.Getter;
import shop.project.mall.domain.user.User;

@Entity
@Getter
public class ArticleLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="articleLikeId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "articleId")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId")
    private User user;

    private boolean deleted;
}
