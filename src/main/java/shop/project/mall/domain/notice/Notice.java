package shop.project.mall.domain.notice;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.project.mall.domain.constant.NoticeType;
import shop.project.mall.domain.user.User;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="noticeId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId")
    private User user;

    @Enumerated(EnumType.STRING)
    private NoticeType noticeType;

    private String title ;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private int readCount;

    private int sort;

    private boolean deleted;

    @Builder
    public Notice(User user,
                  NoticeType noticeType,
                  String title,
                  String content,
                  int readCount,
                  int sort,
                  boolean deleted) {
        this.user = user;
        this.noticeType = noticeType;
        this.title = title;
        this.content = content;
        this.readCount = readCount;
        this.sort = sort;
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notice that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
