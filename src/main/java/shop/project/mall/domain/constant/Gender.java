package shop.project.mall.domain.constant;

import lombok.Getter;


public enum Gender {
    M("MALE"),
    F("FEMALE");


    @Getter
    private final String value;

    Gender(String value) {
        this.value = value;
    }

}
