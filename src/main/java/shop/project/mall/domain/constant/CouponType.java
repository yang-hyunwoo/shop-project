package shop.project.mall.domain.constant;

import lombok.Getter;

public enum CouponType {

    N("NORMAL"),;


    @Getter
    private final String value;

    CouponType(String value) {
        this.value = value;
    }
}
