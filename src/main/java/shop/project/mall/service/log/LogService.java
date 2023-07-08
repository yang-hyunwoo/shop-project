package shop.project.mall.service.log;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import shop.project.mall.auth.LoginUser;
import shop.project.mall.dto.request.log.LogReqDto;
import shop.project.mall.repository.log.LogRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class LogService {

    private final LogRepository logRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logInsert(LogReqDto logReqDto , LoginUser loginUser) {
        logRepository.save(logReqDto.toEntity(loginUser));
    }

}
