package shop.project.mall.domain.chat;

import jakarta.persistence.*;
import lombok.Getter;
import shop.project.mall.domain.constant.ChatRoomType;

import java.util.Objects;

@Entity
@Getter
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chatRoomId")
    private Long id;

    @Column(nullable = false)
    private String roomName;

    private boolean publicRoom;

    @Enumerated(EnumType.STRING)
    private ChatRoomType chatWay;

    private String roomPassword;

    private int roomPersonIngCount;

    private int roomMaxCount;

    private boolean deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatRoom that)) return false;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
