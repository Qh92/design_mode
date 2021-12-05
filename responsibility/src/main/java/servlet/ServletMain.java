package servlet;

import java.util.ArrayList;

/**
 * 在论坛中发表文章
 * 后台要经过信息处理才可以发表或者进入数据库
 *
 * @author Qh
 * @version 1.0
 * @date 2021-11-28 8:47
 */
public class ServletMain {
    public static void main(String[] args) {

        Request request = new Request();
        request.str = "大家好:),<script>,欢迎访问qinhao.com，大家都是996";
        Response response = new Response();
        response.str = "";

        FilterChain filterChain = new FilterChain();
        filterChain.add(new HTMLFilter()).add(new SensitiveFilter());
        filterChain.doFilter(request,response);

        System.out.println(request.str);

    }
}


class Msg {
    String name;
    String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}

/**
 * 过滤器
 */
interface Filter {
    boolean doFilter(Request request,Response response,FilterChain filterChain);
}

class Request {
    String str;
}

class Response {
    String str;
}

class HTMLFilter implements Filter {

    @Override
    public boolean doFilter(Request request,Response response,FilterChain filterChain) {
        //处理当前filter的事情
        System.out.println("HTMLFilter before");
        request.str = request.str.replace('<', '[').replace('>', ']');
        //调用下一个filter
        filterChain.doFilter(request, response);
        System.out.println("HTMLFilter after");
        return true;
    }
}

class SensitiveFilter implements Filter {

    @Override
    public boolean doFilter(Request request,Response response,FilterChain filterChain) {
        System.out.println("SensitiveFilter before");
        request.str = request.str.replaceAll("996", "995");
        //调用下一个filter
        filterChain.doFilter(request, response);
        System.out.println("SensitiveFilter after");
        return true;
    }
}

abstract class FilterChainAdapter implements Filter {
    @Override
    public boolean doFilter(Request request, Response response, FilterChain filterChain) {
        return true;
    }
}

class FilterChain extends FilterChainAdapter {

    int index = 0;

    ArrayList<Filter> filters = new ArrayList<>();

    public FilterChain add(Filter filter) {
        filters.add(filter);
        return this;
    }

    public boolean doFilter(Request request,Response response) {
        if (index > filters.size() - 1) {
            return false;
        }
        return filters.get(index++).doFilter(request, response, this);
    }
}


