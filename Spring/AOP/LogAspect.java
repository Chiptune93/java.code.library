import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    // 로그 저장용 서비스
    @Autowired
    LogSvc svc;

    /**
     * Log Aspect를 적용할 패키지/타입/경로
     * 
     * ex) controller 경로 / service 경로 / 메소드 등
     * 특정 폴더(패키지) : com.example.controller.menuMngt..*.*(..)
     * 특정 메소드(파라미터포함) : com.example.service.findId(HashMap<String, Object>)
     * 
     * [구조]
     * 1. * : 리턴 타입 지정 (*:anything | public string | ...)
     * 2. com.acaas.admin.controller..*.* : 패키지 경로 및 메소드 명 등
     * 3. (..) : 파라미터 타입
     * 
     * .. 더 많은 정보 : https://www.baeldung.com/spring-aop-pointcut-tutorial
     * 
     * '!@annotation(com.acaas.admin.aspect.NoLogging)'
     * NoLogging 어노테이션이 붙은 메소드는 제외한다.
     */
    @Pointcut("execution(* com.acaas.admin.controller..*.*(..)) && !@annotation(com.acaas.admin.aspect.NoLogging)")
    private void cut() {
    }

    /**
     * 메소드 전 구역
     * 
     * @param jp
     */
    @Around("cut()")
    public void Around(JoinPoint jp) {
        logInsert(jp);
    }

    /**
     * 메소드 실행 전
     * 
     * @param jp
     */
    @Before("cut()")
    public void Before(JoinPoint jp) {
        String logType = "Before";
        logInsert(jp, logType, "-", "");
    }

    /**
     * 메소드 실행 후 <기본>
     * !! AfterReturning 과 Throwing 을 사용한다면, 기본 After는 사용하지 말아야 중복 실행 방지 가능함. !!
     * 
     * @param jp
     */
    /*
     * @After("cut()")
     * public void After(JoinPoint jp) {
     * String logType = "After";
     * logInsert(jp, logType);
     * }
     */

    /**
     * 메소드 정상 종료 후
     * 
     * @param jp
     */
    @AfterReturning("cut()")
    public void AfterReturning(JoinPoint jp) {
        String logType = "AfterReturning";
        logInsert(jp, logType, "200", "");
    }

    /**
     * 메소드 에러 ( Exception ) 리턴 경우
     * 
     * @param jp
     */
    @AfterThrowing(value = "cut()", throwing = "e")
    public void AfterThrowing(JoinPoint jp, Exception e) {
        String logType = "AfterThrowing";
        logInsert(jp, logType, "500", e.getMessage());
    }

    /**
     * 실행된 메소드 명 가져오기
     * 
     * @param joinPoint
     * @return
     */
    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }

    /**
     * 로그 작업 구간
     * 커스터마이징 구간
     * 
     * @param jp
     * @param logType
     * @param status
     * @param errorLog
     * @return
     */
    private int logInsert(JoinPoint jp, String logType, String status, String errorLog) {
        // 메소드 명 : 필요한 경우 사용
        Method method = getMethod(jp);
        // 메소드 실행 시 입력된 파라미터 가져오기
        Object[] args = jp.getArgs();
        // 파라미터 내용 가져오기
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        for (Object arg : args) {
            String type = arg.getClass().getSimpleName();
            // 특정 타입만 가져오기
            if (type.equals("HashMap<String, Object>")) {
                paramMap.putAll((HashMap<String, Object>) arg);
            }
        }
        // url check
        String chkUrl = paramMap.getStr("mappingUrl");
        boolean pass = this.chkUrl(chkUrl, paramMap);

        // url pass ?
        if (pass) {
            // 로그 테이블에 저장
            HashMap<String, Object> logMap = new HashMap<String, Object>();

            String userId = "";
            String userNm = "";
            if (JavaUtil.NVL(paramMap.get("session"), "").equals("")) {
                userId = "No Session :: userId";
                userNm = "No Session :: userNm";
            } else {
                userId = paramMap.get("session").getStr("id");
                userNm = paramMap.get("session").getStr("nm");
            }
            String url = paramMap.getStr("mappingUrl");
            String description = this.getDescription(url, paramMap);
            String urlParameter = paramMap.toString();

            logMap.put("logType", logType);
            logMap.put("userId", userId);
            logMap.put("userNm", userNm);
            logMap.put("url", url);
            logMap.put("description", description);
            logMap.put("urlParameter", urlParameter);
            logMap.put("status", status);
            logMap.put("errorLog", errorLog);
            logger.info("[Aspect] " + logType + " logMap : " + logMap);
            return svc.logInsert(logMap);
        } else {
            // none
            return -500;
        }
    }

    /**
     * URL check <DB>
     * 
     * @param url
     * @param paramMap
     * @return
     */
    public boolean chkUrl(String url, HashMap<String, Object> paramMap) {
        String splitUrl = url.split("/admin")[1];
        logger.info("[LOG] chkUrl : " + splitUrl);
        // 1. 블랙리스트 방식으로 제외 URL 체크
        if (svc.chkTask(splitUrl, "black") > 0) {
            return false;
        } else {
            // 2. 화이트리스트 방식으로 DB에 등록된 task와 일치하는지 체크
            if (svc.chkTask(splitUrl, "white") > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 해당 작업 매핑된 작업내용 가져오기 <DB>
     * 
     * @param url
     * @param paramMap
     * @return
     */
    public String getDescription(String url, HashMap<String, Object> paramMap) {
        /* String splitUrl = url.split("/admin")[1]; */
        String splitUrl = url;
        return svc.getDescription(splitUrl);
    }

}
