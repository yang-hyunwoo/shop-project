package shop.project.mall.domain.log;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.project.mall.domain.common.AuditingFields;
import shop.project.mall.domain.user.User;

@NoArgsConstructor
@Getter
@Entity
public class Log extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "logId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId")
    private User user;

    private String uuid;

    private boolean sucesStts;

    private String methodName;

    private String httpMethod;

    @Column(length = 4000)
    private String response;
    @Column(length = 4000)
    private String request;
    @Column(length = 4000)
    private String errorMsg;

    @Builder
    public Log(User user,
               String uuid,
               boolean sucesStts,
               String methodName,
               String httpMethod,
               String response,
               String request,
               String errorMsg) {
        this.user = user;
        this.uuid = uuid;
        this.sucesStts = sucesStts;
        this.methodName = methodName;
        this.httpMethod = httpMethod;
        this.response = response;
        this.request = request;
        this.errorMsg = errorMsg;
    }
}
