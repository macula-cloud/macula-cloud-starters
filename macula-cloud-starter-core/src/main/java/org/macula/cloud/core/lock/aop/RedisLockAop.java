package org.macula.cloud.core.lock.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.macula.cloud.core.lock.annotation.RedisLock;
import org.macula.cloud.core.lock.distributedlock.DistributedLock;
import org.macula.cloud.core.lock.enums.LockFailedPolicy;
import org.springframework.lang.NonNull;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
public class RedisLockAop extends AbstractLockAop {

	private DistributedLock distributedLock;

	public RedisLockAop(@NonNull DistributedLock distributedLock) {
		this.distributedLock = distributedLock;
	}

	@Around("@annotation(redisLock)")
	public Object execute(ProceedingJoinPoint joinPoint, RedisLock redisLock) throws Throwable {
		String lockKey = redisLock.value();
		Method method = getMethod(joinPoint);
		String key = redisLock.prefix() + ":" + parseKey(lockKey, method, joinPoint.getArgs());
		int retryTimes = redisLock.action().equals(LockFailedPolicy.CONTINUE) ? redisLock.retryTimes() : 0;

		boolean lock = distributedLock.lock(key, redisLock.expireTime(), retryTimes, redisLock.sleepMills());
		if (lock) {
			// 得到锁,执行方法，释放锁
			log.debug("get lock success : " + key);
			try {
				return joinPoint.proceed();
			} finally {
				boolean releaseResult = distributedLock.releaseLock(key);
				log.debug("release redis lock : " + key + (releaseResult ? " success" : " failed"));
			}
		}
		throw new RuntimeException(String.format("Error get redis distributed lock %s", redisLock.value()));
	}
}
