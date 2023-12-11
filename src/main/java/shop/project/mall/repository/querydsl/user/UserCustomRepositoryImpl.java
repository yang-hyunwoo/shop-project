package shop.project.mall.repository.querydsl.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import shop.project.mall.domain.user.QUser;
import shop.project.mall.domain.user.User;

public class UserCustomRepositoryImpl extends QuerydslRepositorySupport implements UserCustomRepository {

    private JPAQueryFactory queryFactory;

    public UserCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        super(User.class);
        this.queryFactory = queryFactory;
    }

    QUser user = QUser.user;

    @Override
    public void updatePassword(Long id, String password) {
        queryFactory.update(user)
                .set(user.password, password)
                .where(user.id.eq(id));
    }
}
