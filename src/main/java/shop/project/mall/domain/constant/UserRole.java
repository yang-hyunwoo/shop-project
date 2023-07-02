package shop.project.mall.domain.constant;

import lombok.Getter;

public enum UserRole {

    SUPER("루트") , ADMIN("관리자") , USER("사용자");

    @Getter
    private final String value;

    UserRole(String value) {
        this.value = value;
    }
}
