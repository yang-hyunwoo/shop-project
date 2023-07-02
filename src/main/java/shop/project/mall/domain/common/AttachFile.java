package shop.project.mall.domain.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(indexes = {
        @Index(columnList = "attachFileChildId")
})
@IdClass(AttachFileId.class)
public class AttachFile {

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

}
