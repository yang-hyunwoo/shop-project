package shop.project.mall.domain.prdc;

import jakarta.persistence.*;
import lombok.Getter;

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




}
