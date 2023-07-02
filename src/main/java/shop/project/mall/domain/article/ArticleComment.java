package shop.project.mall.domain.article;

import jakarta.persistence.*;
import lombok.Getter;
import shop.project.mall.domain.user.User;

@Entity
@Getter
public class ArticleComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="articleCommentId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "articleId")
    private Article article;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private boolean deleted;

    private boolean rprtStts;
}
