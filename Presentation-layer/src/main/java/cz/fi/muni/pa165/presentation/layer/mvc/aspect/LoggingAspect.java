/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.presentation.layer.mvc.aspect;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.JoinPoint;


/**
 *
 * @author Peter Franek
 */
@Component
@Aspect
public class LoggingAspect {
    
    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    
    @Pointcut("execution(public * cz.fi.muni.pa165.presentation.layer.mvc.controllers.*.create(*, *, ..))")
    private void allCreateMethods() {
    }
    
    @Pointcut("execution(public * cz.fi.muni.pa165.presentation.layer.mvc.controllers.*.delete(*, *, ..))")
    private void allDeleteMethods() {
    }
    
    @Before("allCreateMethods() || allDeleteMethods()")
    private void logMethodExecution(JoinPoint point) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	String currentTime = dateFormat.format(new Date());
        
        String method = point.getSignature().getName();
        String target = point.getTarget().getClass().getSimpleName();
        logger.error("Method of " + target +": "+ method + " at " + currentTime + " executed.");      
    }
}
