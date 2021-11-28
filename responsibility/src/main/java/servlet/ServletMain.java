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

        FilterChain filterChain = new FilterChain();
        filterChain.add(new HTMLFilter()).add(new SensitiveFilter());

        Request request = new Request();
        Response response = new Response();
        filterChain.doFilter(request,response);

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

}

class Response {

}

class HTMLFilter implements Filter {

    @Override
    public boolean doFilter(Request request,Response response,FilterChain filterChain) {
        //处理当前filter的事情
        System.out.println("HTMLFilter before");
        if (true) {
            return false;
        }
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
        if (!filters.get(index++).doFilter(request, response, this)){
            return false;
        }
        return true;
    }
}


