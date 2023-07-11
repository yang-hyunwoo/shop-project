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
public class ArticleComment extends AuditingFields {

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

    @Builder
    public ArticleComment(User user,
                          Article article,
                          String content,
                          boolean deleted,
                          boolean rprtStts) {
        this.user = user;
        this.article = article;
        this.content = content;
        this.deleted = deleted;
        this.rprtStts = rprtStts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
