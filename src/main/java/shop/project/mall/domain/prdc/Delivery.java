package shop.project.mall.domain.prdc;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

@Entity
@Getter
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="deliveryId")
    private Long id;

    @Column(length = 255)
    private String deliveryUid;

    @Column(length = 10)
    private String deliveryStore;

    @Column(length = 10)
    private String deliveryStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Delivery that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
