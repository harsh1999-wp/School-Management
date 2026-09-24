package com.harsh.School.aspect;

import com.harsh.School.annotation.TimeTrack;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.query.results.internal.TableGroupImpl;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingOffTime {

    //@Around("@annotation(com.harsh.School.annotation.TimeTrack)")//type matching form
    @Around("@annotation(trackTime)")
    public Object  measureTime(ProceedingJoinPoint joinPoint ,
                                TimeTrack trackTime
                                ) throws Throwable{
        long startTime = System.currentTimeMillis();

        try{
            return joinPoint.proceed();
        }
        finally {
            long endTime = System.currentTimeMillis();
            long duration = startTime - endTime;

            String methodName = joinPoint.getSignature().getName();

            String operationName = trackTime.operation();

            if(operationName.isBlank()){
                operationName = methodName;
            }

            long warnThreshold = trackTime.warnAfter();

            if(duration >= warnThreshold){
                System.out.println("Slow Duration Alert" + "Time taken" + operationName + ":" +duration );
            }
            else{
                System.out.println("Method name is " + operationName +"has taken" + duration);
            }



        }
    }
}
