package shop.project.mall.domain.user;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class EmailAuth {

    private static final Long MAX_EXPIRE_TIME = 5L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emailAuthId")
    private Long id;

    private String email;

    private String authToken;

    private Boolean expired;

    private LocalDateTime expireDate;

}
