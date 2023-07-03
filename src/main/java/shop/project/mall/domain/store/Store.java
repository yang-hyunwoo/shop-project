package shop.project.mall.domain.store;

import jakarta.persistence.*;
import lombok.Getter;
import shop.project.mall.domain.common.AttachFile;

import java.util.Objects;

@Entity
@Getter
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="storeId")
    private Long id;

    @Column(nullable = false , length = 50)
    private String storeName;

    @OneToOne(fetch = FetchType.LAZY)
    private AttachFile storeFileId;

    private String comment;

    @Column(length = 1000)
    private String addr;

    @Column(length = 50)
    private String tel;

    @Column(length = 3000)
    private String detail;

    private boolean deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Store that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

