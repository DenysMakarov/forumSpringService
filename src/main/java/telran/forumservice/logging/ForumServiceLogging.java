package telran.forumservice.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;


@Aspect
@Slf4j
@Service
public class ForumServiceLogging {
//    @Pointcut("execution(public * telran.forumservice.service.ForumServiceImpl.*(..))") // все public методы (любые)
//        @Pointcut("execution(public java.util.List<telran.forumservice.dto.PostDto> telran.forumservice.service.ForumServiceImpl.*(..))") // все public Iterable методы (любые)
    @Pointcut("execution(public * telran.forumservice.service.ForumServiceImpl.find*(..))") // все public начинающиеся с find
    public void bulkingFind(){}

    @Pointcut("execution(public * telran.forumservice.service.ForumServiceImpl.findPostById(String)) && args(postId)")
    public void findById(String postId){}

    @Around("bulkingFind()")
    public Object bulkingFindLogging(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        long t1 = System.currentTimeMillis();
        Object returnVal = pjp.proceed(args);
        long t2 = System.currentTimeMillis();
        log.info("method - {}, duration={}", pjp.getSignature().getName(), (t2 - t1));
        return returnVal;
    }

    @Pointcut("@annotation(PostLogger) && args(postId, ..)") // все кто будет помечен PostLogger -> все будут под поинткат
    public void annotated(String postId){}

    @Before("findById(postId)")
    public void logLogging(String postId){
        log.info("post with id {}", postId);
    }

    @AfterReturning("annotated(postId)")
    public void updatePost(String postId){

    }
}
