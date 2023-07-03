package shop.project.mall.domain.prdc;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

@Entity
@Getter
public class PrdcCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="prdcCodeId")
    private Long id;

    @Column(length = 50)
    private String prdcName;

    private Long prdcCode;

    private boolean deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrdcCode that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
