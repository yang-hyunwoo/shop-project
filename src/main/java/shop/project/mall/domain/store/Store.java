package shop.project.mall.domain.store;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.project.mall.domain.common.AttachFile;
import shop.project.mall.domain.common.AuditingFields;

import java.util.Objects;
@NoArgsConstructor
@Getter
@Table(indexes = {
        @Index(columnList = "storeName"),
})
@Entity
public class Store extends AuditingFields {

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
    @Builder
    public Store(String storeName,
                 AttachFile storeFileId,
                 String comment,
                 String addr,
                 String tel,
                 String detail,
                 boolean deleted) {
        this.storeName = storeName;
        this.storeFileId = storeFileId;
        this.comment = comment;
        this.addr = addr;
        this.tel = tel;
        this.detail = detail;
        this.deleted = deleted;
    }

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

