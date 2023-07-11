package shop.project.mall.domain.article;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.project.mall.domain.common.AuditingFields;
import shop.project.mall.domain.user.User;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Entity
public class ArticleCommentRprt extends AuditingFields {

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


    @Builder
    public ArticleCommentRprt(ArticleComment articleComment,
                              User user,
                              boolean deleted) {
        this.articleComment = articleComment;
        this.user = user;
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleCommentRprt that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
