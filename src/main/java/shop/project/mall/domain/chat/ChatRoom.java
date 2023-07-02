package shop.project.mall.domain.chat;

import jakarta.persistence.*;
import lombok.Getter;
import shop.project.mall.domain.constant.ChatRoomType;

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

}
