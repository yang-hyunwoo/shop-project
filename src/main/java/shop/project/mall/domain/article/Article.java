package shop.project.mall.domain.article;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.project.mall.domain.common.AuditingFields;
import shop.project.mall.domain.constant.ArticleType;
import shop.project.mall.domain.user.User;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Entity
public class Article extends AuditingFields {

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

    @Builder
    public Article(User user,
                   ArticleType articleType,
                   String title,
                   String content,
                   int readCount,
                   boolean deleted,
                   boolean rprtStts) {
        this.user = user;
        this.articleType = articleType;
        this.title = title;
        this.content = content;
        this.readCount = readCount;
        this.deleted = deleted;
        this.rprtStts = rprtStts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
