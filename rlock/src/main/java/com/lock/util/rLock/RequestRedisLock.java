package com.lock.util.rLock;


import com.lock.util.redis.JedisUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Aspect
@Component
public class RequestRedisLock {
    private static final Logger log = Logger.getLogger(RequestRedisLock.class);

    @Autowired
    private JedisUtil jedisUtil;

    @Around("@annotation(lock)")
    public void requestLockBefore(ProceedingJoinPoint proceedingJoinPoint , RLock lock){

        String flag = jedisUtil.jedisSet("lock_"+lock.name() , "1" , "NX" , "EX" , lock.expireTime());
        log.info(lock.name() +"  redis开关状态--"+flag);
        if(StringUtils.isEmpty(flag)){
            return;
        }
        try {
            if("OK".equalsIgnoreCase(flag)){
                proceedingJoinPoint.proceed();
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error(lock.name() + " redis锁异常了");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error(lock.name() + " redis锁异常了");
        }
    }
}
