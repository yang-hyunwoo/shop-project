package shop.project.mall.domain.article;

import jakarta.persistence.*;
import lombok.Getter;
import shop.project.mall.domain.user.User;

@Entity
@Getter
public class ArticleCommentRprt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="articleCommentRprtId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "articleCommentId")
    private ArticleComment articleComment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId")
    private User user;

    private boolean deleted;

}
