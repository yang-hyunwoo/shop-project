package shop.project.mall.domain.article;

import jakarta.persistence.*;
import lombok.Getter;
import shop.project.mall.domain.constant.ArticleType;
import shop.project.mall.domain.user.User;

@Entity
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="articleId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId")
    private User user;

    @Enumerated(EnumType.STRING)
    private ArticleType articleType;

    @Column(length = 1000)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private int readCount;

    private boolean deleted;

    private boolean rprtStts;

}
