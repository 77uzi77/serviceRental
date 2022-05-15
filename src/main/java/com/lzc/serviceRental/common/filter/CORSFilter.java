package com.lzc.serviceRental.common.filter;

import com.google.gson.Gson;
import com.lzc.serviceRental.common.exception.JSONResponse;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 允许跨域过滤器
 * @Author lzc
 * @Date 2022/5/2
 * @Version 1.0
 */
@WebFilter(filterName = "cORSFilter",urlPatterns = "/*")
@Order(value = Integer.MIN_VALUE)
public class CORSFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse) res;

        if(response.getHeader("Access-Control-Allow-Origin") == null){
            String origin = request.getHeader("Origin");
            if(origin != null){
                response.setHeader("Access-Control-Allow-Origin", origin);
            }else{
                response.setHeader("Access-Control-Allow-Origin", "*");
            }
        }else{
            System.out.println("Access-Control-Allow-Origin already exist!");
        }
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT,PATCH, HEAD");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, ContentType, Content-Type, Depth, User-Agent, X-File-Size," +
                " X-Requested-With, X-Requested-By, If-Modified-Since, X-File-Name, X-File-Type, Cache-Control, Origin,token,boundary,refresh_token,timestamp");
        response.addHeader("Access-Control-Max-Age", "3600");

        //不写死的方法,支持所有的自定义的头，根据请求信息，返回
        String headers = request.getHeader("Access-Control-Request-Headers");
        if(headers!=null){
            response.addHeader("Access-Control-Allow-Headers",headers);
        }
        //这个很关键，要不然ajax调用时浏览器默认不会把这个token的头属性返给JS
        String exposeHeaders = "access-control-expose-headers";
        if(!response.containsHeader(exposeHeaders)) {
            response.setHeader(exposeHeaders, "token");
        }

        // OPTIONS请求特殊处理，直接返回成功，不进行后续处理链的处理
        if(request.getMethod().equals("OPTIONS")){
            handleOPTIONS(response);
            return;
        }
        chain.doFilter(request, response);
    }

    /**
     * OPTIONS请求特殊处理，直接返回成功，不进行后续处理链的处理
     * @param response
     * @throws IOException
     */
    private void handleOPTIONS(HttpServletResponse response) throws IOException {
        JSONResponse jsonResponse = new JSONResponse().success();
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(response));
    }

    @Override
    public void init(FilterConfig filterConfig) {}
    @Override
    public void destroy() {}
}