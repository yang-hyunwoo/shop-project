package shop.project.mall.domain.common;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@Getter
@IdClass(AttachFileId.class)
@Table(indexes = {
        @Index(columnList = "attachFileChildId")
})
@Entity

public class AttachFile extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="attachFileId")
    private Long id;

    @Id
    @Setter
    private int attachFileChildId;

    @Column(nullable = false , length=300)
    private String fileName;

    @Column(nullable = false , length=300)
    private String filePath;

    @Column(nullable = false)
    private Long fileSize;

    @Column(nullable = false , length=300)
    private String originFileName;

    @Column(length=300)
    private String thumbFilePath;

    private boolean deleted;

    @Builder
    public AttachFile(int attachFileChildId,
                      String fileName,
                      String filePath,
                      Long fileSize,
                      String originFileName,
                      String thumbFilePath,
                      boolean deleted) {
        this.attachFileChildId = attachFileChildId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.originFileName = originFileName;
        this.thumbFilePath = thumbFilePath;
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttachFile that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
