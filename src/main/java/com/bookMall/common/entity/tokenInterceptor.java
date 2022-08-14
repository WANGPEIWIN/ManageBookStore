package com.bookMall.common.entity;


import com.bookMall.common.util.tokenUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class tokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        //编码格式
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        //获取token数据
        String token = request.getHeader("Authorization");
        if(token == null){
            writer.write(new responseJson(responseCode.NO_TOKEN).toString());
            writer.close();
            return false;
        }else{
            if(tokenUtils.verify(token)){
                return true;
            }else{
                writer.write(new responseJson(responseCode.TOKEN_EXPIRED).toString());
                writer.close();
                return false;
            }
        }
    }
}
