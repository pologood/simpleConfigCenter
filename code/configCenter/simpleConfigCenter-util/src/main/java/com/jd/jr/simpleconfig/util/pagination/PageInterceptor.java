package com.jd.jr.simpleconfig.util.pagination;

import com.jd.jr.simpleconfig.util.PageUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
public class PageInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(PageInterceptor.class);

    /*    @Pointcut("@annotation(PaginationAnnotion)")
        public void aopPoint() {
        }*/
    @After(value = "execution(* *(..)) && @annotation(pagination)", argNames = "jp,pagination")
    public Object doRoute(JoinPoint jp, PaginationAnnotion pagination) throws Throwable {
        long t1 = System.currentTimeMillis();
        boolean result = true;
        String pageParaName = pagination.pageName();
        int pageSize = pagination.pageSize();
        Object[] args = jp.getArgs();
        HttpServletRequest request = null;
        ModelMap model = null;
        String pageParaValue = null;
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                if (arg == null) {
                    continue;
                }
/*                if (arg instanceof List && ((List) arg).size() > 0) {
                    arg = ((List) arg).get(0);
                }*/
                if (arg instanceof HttpServletRequest) {
                    request = (HttpServletRequest) arg;
                    pageParaValue = request.getParameter(pageParaName);
                }
                if (arg instanceof ModelMap) {
                    model = (ModelMap) arg;
                }
            }
        }

        //  jp.proceed();
        //ModelMap model = (ModelMap) resultObj;
        if (model.get("count") != null) {
            Integer count = Integer.parseInt( ""+model.get("count"));
            pageParaValue = pageParaValue == null ? "1" : pageParaValue;
            String joinPara = PageUtil.joinParameter(request, new String[]{pageParaName}, true);
            String pagestr = PageUtil.getPageStr(joinPara, pageSize, Integer.parseInt(pageParaValue), count);
            model.addAttribute("pagestr", pagestr);
        }
        logger.info("分页组合时间{}", System.currentTimeMillis() - t1);
        return result;
    }

    private Method getMethod(JoinPoint jp) throws NoSuchMethodException {
        Signature sig = jp.getSignature();
        MethodSignature msig = (MethodSignature) sig;
        return getClass(jp).getMethod(msig.getName(), msig.getParameterTypes());
    }

    private Class<? extends Object> getClass(JoinPoint jp)
            throws NoSuchMethodException {
        return jp.getTarget().getClass();
    }

}
