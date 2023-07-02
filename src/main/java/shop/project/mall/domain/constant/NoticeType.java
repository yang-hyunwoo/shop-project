package shop.project.mall.domain.constant;

import lombok.Getter;

public enum NoticeType {

    N("NORMAL"),
    ;


    @Getter
    private final String value;

    NoticeType(String value) {
        this.value = value;
    }
}
