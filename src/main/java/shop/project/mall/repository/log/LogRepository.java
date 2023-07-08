package shop.project.mall.repository.log;


import org.springframework.data.jpa.repository.JpaRepository;
import shop.project.mall.domain.log.Log;

public interface LogRepository extends JpaRepository<Log, Long> {


}
