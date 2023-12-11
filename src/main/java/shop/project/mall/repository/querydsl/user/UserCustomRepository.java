package shop.project.mall.repository.querydsl.user;

public interface UserCustomRepository {

    void updatePassword(Long id, String password);
}
