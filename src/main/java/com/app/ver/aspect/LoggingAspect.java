package com.app.ver.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Objects;

/**
 * LoggingAspect is an Aspect-Oriented Programming (AOP) component that intercepts and logs
 * key events in the application, particularly within the controller and service layers.
 *
 * It logs details about incoming HTTP requests, execution of service methods, return values,
 * and exceptions. This is useful for debugging, monitoring, and auditing purposes.
 *
 * Annotations used:
 * <ul>
 *   <li>{@code @Aspect}: Declares this class as an aspect.</li>
 *   <li>{@code @Component}: Registers the aspect as a Spring bean for dependency injection and component scanning.</li>
 *   <li>{@code @Slf4j}: Provides a logger instance via Lombok for logging operations.</li>
 * </ul>
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

    /**
     * Pointcut that matches all public methods in any class within the
     * {@code com.app.ver.controller} package.
     *
     * This pointcut is used to intercept controller layer methods.
     */
    @Pointcut("execution(public * com.app.ver.controller.*.*(..))")
    public void controllerLog() {
    }

    /**
     * Pointcut that matches all public methods in any class within the
     * {@code com.app.ver.service} package.
     *
     * This pointcut is used to intercept service layer methods.
     */
    @Pointcut("execution(public * com.app.ver.service.*.*(..))")
    public void serviceLog() {
    }

    /**
     * Advice that executes before any method matched by the {@link #controllerLog()} pointcut.
     *
     * It retrieves the current HTTP request and logs the following details:
     * <ul>
     *   <li>Client IP address</li>
     *   <li>Requested URL</li>
     *   <li>HTTP method (e.g., GET, POST)</li>
     *   <li>The controller class and method being invoked</li>
     * </ul>
     *
     * @param jp the join point providing reflective access to the method being executed.
     */
    @Before("controllerLog()")
    public void doBeforeController(JoinPoint jp) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        log.info("NEW REQUEST:\n" +
                        "IP : {}\n" +
                        "URL : {}\n" +
                        "HTTP_METHOD : {}\n" +
                        "CONTROLLER_METHOD : {}.{}",
                request.getRemoteAddr(),
                request.getRequestURL().toString(),
                request.getMethod(),
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName());
    }

    /**
     * Advice that executes before any method matched by the {@link #serviceLog()} pointcut.
     *
     * It logs the fully qualified name of the service method that is about to be executed.
     *
     * @param jp the join point providing reflective access to the method being executed.
     */
    @Before("serviceLog()")
    public void doBeforeService(JoinPoint jp) {
        log.info("RUN SERVICE:\n" +
                        "SERVICE_METHOD : {}.{}",
                jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
    }

    /**
     * Advice that executes after a controller method (matched by {@link #controllerLog()})
     * returns successfully.
     *
     * It logs the returned object from the controller method and indicates the end of the request.
     *
     * @param returnObject the object returned by the controller method.
     */
    @AfterReturning(returning = "returnObject", pointcut = "controllerLog()")
    public void doAfterReturning(Object returnObject) {
        log.info("\nReturn value: {}\n" +
                        "END OF REQUEST",
                returnObject);
    }

    /**
     * Advice that executes when a controller method (matched by {@link #controllerLog()})
     * throws an exception.
     * <p>
     * It logs the exception details along with the arguments passed to the method.
     *
     * @param jp the join point providing reflective access to the method where the exception occurred.
     * @param ex the exception thrown by the controller method.
     */
    @AfterThrowing(throwing = "ex", pointcut = "controllerLog()")
    public void throwsException(JoinPoint jp, Exception ex) {
        log.error("Request throw an exception. Cause - {}. {}",
                Arrays.toString(jp.getArgs()), ex.getMessage());
    }
}