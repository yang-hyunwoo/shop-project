package shop.project.mall.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
import shop.project.mall.domain.common.AttachFile;
import shop.project.mall.domain.constant.Gender;
import shop.project.mall.domain.constant.UserRole;
import shop.project.mall.domain.store.Store;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
public class UserHist {

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
