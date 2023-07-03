package shop.project.mall.domain.point;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

@Entity
@Getter
public class PointCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pointCodeId")
    private Long id;

    @Column(nullable = false)
    private String pointCode;

    @Column(nullable = false)
    private String pointCodeName;

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
