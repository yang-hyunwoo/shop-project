package shop.project.mall.domain.prdc;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Entity
public class PrdcCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="prdcCodeId")
    private Long id;

    @Column(length = 50)
    private String prdcName;

    private Long prdcCode;

    private boolean deleted;
    @Builder
    public PrdcCode(String prdcName,
                    Long prdcCode,
                    boolean deleted) {
        this.prdcName = prdcName;
        this.prdcCode = prdcCode;
        this.deleted = deleted;
    }

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
