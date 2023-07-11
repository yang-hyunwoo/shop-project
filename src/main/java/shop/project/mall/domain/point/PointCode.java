package shop.project.mall.domain.point;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.project.mall.domain.common.AuditingFields;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Table(indexes = {
        @Index(columnList = "pointCodeName"),
})
@Entity
public class PointCode extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pointCodeId")
    private Long id;

    @Column(nullable = false)
    private String pointCode;

    @Column(nullable = false)
    private String pointCodeName;

    @Builder
    public PointCode(String pointCode,
                     String pointCodeName) {
        this.pointCode = pointCode;
        this.pointCodeName = pointCodeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PointCode that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
