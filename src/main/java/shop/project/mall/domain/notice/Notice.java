package shop.project.mall.domain.notice;

import jakarta.persistence.*;
import lombok.Getter;
import shop.project.mall.domain.constant.NoticeType;
import shop.project.mall.domain.user.User;

@Entity
@Getter
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

}
