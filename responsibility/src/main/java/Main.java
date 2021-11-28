import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * 在论坛中发表文章
 * 后台要经过信息处理才可以发表或者进入数据库
 *
 * @author Qh
 * @version 1.0
 * @date 2021-11-28 8:47
 */
public class Main {
    public static void main(String[] args) {
        Msg msg = new Msg();

        msg.setMsg("大家好:),<script>,欢迎访问qinhao.com，大家都是996");

        //v1版 处理msg，这种扩展性不好，如果以后还要添加新的过滤规则，还要改动原来的代码，可以考虑过滤器
        /*String s = msg.getMsg();
        s = s.replace('<', '[').replace('>', ']');
        s = s.replaceAll("996", "995");*/

        //v2版 改用过滤器，这种方式如果新增代码还是要改动代码，可以考虑将所有filter连在一起
        /*new HTMLFilter().doFilter(msg);
        new SensitiveFilter().doFilter(msg);*/

        //v3版 将所有filter串起来，不完整版责任链
        /*ArrayList<Filter> filters = new ArrayList<>();
        filters.add(new HTMLFilter());
        filters.add(new SensitiveFilter());
        for (Filter filter : filters) {
            filter.doFilter(msg);
        }*/

        //v4版 添加一个链条类
        FilterChain filterChain = new FilterChain();
        filterChain.add(new HTMLFilter()).add(new SensitiveFilter());

        FilterChain filterChain2 = new FilterChain();
        filterChain2.add(new FaceFilter()).add(new URLFilter());

        filterChain.add(filterChain2);

        filterChain.doFilter(msg);

        System.out.println(msg.getMsg());
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
    boolean doFilter(Msg msg);
}

class HTMLFilter implements Filter {

    @Override
    public boolean doFilter(Msg msg) {
        String s = msg.getMsg();
        s = s.replace('<', '[').replace('>', ']');
        msg.setMsg(s);
        return true;
    }
}

class SensitiveFilter implements Filter {

    @Override
    public boolean doFilter(Msg msg) {
        String s = msg.getMsg();
        s = s.replaceAll("996", "995");
        msg.setMsg(s);
        return false;
    }
}

class FaceFilter implements Filter {

    @Override
    public boolean doFilter(Msg msg) {
        String s = msg.getMsg();
        s = s.replace(":)", "^V^");
        msg.setMsg(s);
        return true;
    }
}

class URLFilter implements Filter {

    @Override
    public boolean doFilter(Msg msg) {
        String s = msg.getMsg();
        s = s.replace("qinhao.com", "http://www.qinhao.com");
        msg.setMsg(s);
        return true;
    }
}

class FilterChain implements Filter{
    ArrayList<Filter> filters = new ArrayList<>();

    public FilterChain add(Filter filter) {
        filters.add(filter);
        return this;
    }

    @Override
    public boolean doFilter(Msg msg) {
        for (Filter filter : filters) {
            if (!filter.doFilter(msg)) {
                return false;
            }
        }
        return true;
    }
}


