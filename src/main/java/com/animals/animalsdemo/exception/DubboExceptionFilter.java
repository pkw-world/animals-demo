package com.animals.animalsdemo.exception;

import com.alibaba.dubbo.common.utils.ReflectUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.rpc.filter.ExceptionFilter;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author pankewei
 * @date 2021/8/16
 */
public class DubboExceptionFilter extends ExceptionFilter {
    private final Logger logger;

    public DubboExceptionFilter() {
        this(LoggerFactory.getLogger(ExceptionFilter.class));
    }

    public DubboExceptionFilter(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try {
            Result result = invoker.invoke(invocation);
            if (result.hasException() && GenericService.class != invoker.getInterface()) {

                try {
                    Throwable exception = result.getException();

                    // 自定义的异常
                    if (exception instanceof BusinessException){
                        return result;
                    }

                    if (!(exception instanceof RuntimeException) && exception instanceof Exception) {
                        return result;
                    } else {
                        try {
                            Method method = invoker.getInterface().getMethod(invocation.getMethodName(), invocation.getParameterTypes());
                            Class<?>[] exceptionClassses = method.getExceptionTypes();
                            Class[] arr$ = exceptionClassses;
                            int len$ = exceptionClassses.length;

                            for (int i$ = 0; i$ < len$; ++i$) {
                                Class<?> exceptionClass = arr$[i$];
                                if (exception.getClass().equals(exceptionClass)) {
                                    return result;
                                }
                            }
                        } catch (NoSuchMethodException var11) {
                            return result;
                        }

                        this.logger.error("Got unchecked and undeclared exception which called by " + RpcContext.getContext().getRemoteHost() + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName() + ", exception: " + exception.getClass().getName() + ": " + exception.getMessage(), exception);
                        String serviceFile = ReflectUtils.getCodeBase(invoker.getInterface());
                        String exceptionFile = ReflectUtils.getCodeBase(exception.getClass());
                        if (serviceFile != null && exceptionFile != null && !serviceFile.equals(exceptionFile)) {

                            String className = exception.getClass().getName();
                            if (!className.startsWith("java.") && !className.startsWith("javax.")) {
                                return (Result) (exception instanceof RpcException ? result : new RpcResult(new RuntimeException(StringUtils.toString(exception))));
                            } else {
                                return result;
                            }
                        } else {
                            return result;
                        }
                    }
                } catch (Throwable var12) {
                    this.logger.warn("Fail to ExceptionFilter when called by " + RpcContext.getContext().getRemoteHost() + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName() + ", exception: " + var12.getClass().getName() + ": " + var12.getMessage(), var12);
                    return result;
                }
            } else {
                return result;
            }
        } catch (RuntimeException var13) {
            this.logger.error("Got unchecked and undeclared exception which called by " + RpcContext.getContext().getRemoteHost() + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName() + ", exception: " + var13.getClass().getName() + ": " + var13.getMessage(), var13);
            throw var13;
        }
    }
}
