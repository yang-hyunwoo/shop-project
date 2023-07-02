package shop.project.mall.domain.constant;

import lombok.Getter;

public enum ChatRoomType {

    ONE("ONE"),
    MULTI("MULTI");


    @Getter
    private final String value;

    ChatRoomType(String value) {
        this.value = value;
    }
}
