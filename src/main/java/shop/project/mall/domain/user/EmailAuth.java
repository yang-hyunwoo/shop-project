package shop.project.mall.domain.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.project.mall.domain.common.AuditingFields;

import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Entity
public class EmailAuth extends AuditingFields {

    private static final Long MAX_EXPIRE_TIME = 5L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emailAuthId")
    private Long id;

    private String email;

    private String authToken;

    private Boolean expired;

    private LocalDateTime expireDate;

    @Builder
    public EmailAuth(String email,
                     String authToken,
                     Boolean expired,
                     LocalDateTime expireDate) {
        this.email = email;
        this.authToken = authToken;
        this.expired = expired;
        this.expireDate = expireDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailAuth that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
