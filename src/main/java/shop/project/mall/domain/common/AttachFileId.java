package shop.project.mall.domain.common;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AttachFileId implements Serializable {
    private Long id;

    private int attachFileChildId;

public AttachFileId() {}

public AttachFileId(Long id, int attachFileChildId) {
    this.id = id;
    this.attachFileChildId = attachFileChildId;
}

}
