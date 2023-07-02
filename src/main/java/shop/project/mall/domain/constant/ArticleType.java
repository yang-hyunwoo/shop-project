package shop.project.mall.domain.constant;

import lombok.Getter;


public enum ArticleType {
    N("NORMAL"),
    F("FREE");


    @Getter
    private final String value;

    ArticleType(String value) {
        this.value = value;
    }

}
