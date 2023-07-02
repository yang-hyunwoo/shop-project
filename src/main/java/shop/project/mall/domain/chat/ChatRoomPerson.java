package shop.project.mall.domain.chat;

import jakarta.persistence.*;
import lombok.Getter;
import shop.project.mall.domain.user.User;

@Entity
@Getter
public class ChatRoomPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chatRoomPersonId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chatRoomId")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId")
    private User user;
}
