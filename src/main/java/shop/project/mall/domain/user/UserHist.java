package shop.project.mall.domain.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.project.mall.domain.common.AttachFile;
import shop.project.mall.domain.common.AuditingFields;
import shop.project.mall.domain.constant.Gender;
import shop.project.mall.domain.constant.UserRole;
import shop.project.mall.domain.store.Store;

import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Table(indexes = {
                @Index(columnList = "nickname"),
                @Index(columnList = "username"),
                @Index(columnList = "email"),
        })
@Entity
public class UserHist extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private AttachFile attachFile;

    @OneToOne(fetch = FetchType.LAZY)
    private Store store;

    @Column(nullable = false , length = 50)
    private String email;

    @Column(nullable = false ,length = 50)
    private String nickname;

    @Column(nullable = false ,length = 50)
    private String userName;

    @Column(nullable = false , length = 1000)
    private String password;

    @Column(length = 20)
    private String phoneNumber;

    private int point;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String loginType;

    private boolean emailAuth;

    @Enumerated(EnumType.STRING)
    private UserRole auth;

    private boolean authChk;

    private LocalDateTime pwChgDate;

    private boolean useYn;

    private LocalDateTime dromantDay;

    private LocalDateTime deleteDay;

    @Builder
    public UserHist(AttachFile attachFile,
                    Store store,
                    String email,
                    String nickname,
                    String userName,
                    String password,
                    String phoneNumber,
                    int point,
                    Gender gender,
                    String loginType,
                    boolean emailAuth,
                    UserRole auth,
                    boolean authChk,
                    LocalDateTime pwChgDate,
                    boolean useYn,
                    LocalDateTime dromantDay,
                    LocalDateTime deleteDay) {
        this.attachFile = attachFile;
        this.store = store;
        this.email = email;
        this.nickname = nickname;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.point = point;
        this.gender = gender;
        this.loginType = loginType;
        this.emailAuth = emailAuth;
        this.auth = auth;
        this.authChk = authChk;
        this.pwChgDate = pwChgDate;
        this.useYn = useYn;
        this.dromantDay = dromantDay;
        this.deleteDay = deleteDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserHist that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }





}
