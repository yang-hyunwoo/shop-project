package shop.project.mall.domain.chat;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.project.mall.domain.common.AuditingFields;
import shop.project.mall.domain.constant.ChatRoomType;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Table(indexes = {
        @Index(columnList = "roomName"),
})
@Entity
public class ChatRoom extends AuditingFields {

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

    @Builder
    public ChatRoom(String roomName,
                    boolean publicRoom,
                    ChatRoomType chatWay,
                    String roomPassword,
                    int roomPersonIngCount,
                    int roomMaxCount,
                    boolean deleted) {
        this.roomName = roomName;
        this.publicRoom = publicRoom;
        this.chatWay = chatWay;
        this.roomPassword = roomPassword;
        this.roomPersonIngCount = roomPersonIngCount;
        this.roomMaxCount = roomMaxCount;
        this.deleted = deleted;
    }

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
