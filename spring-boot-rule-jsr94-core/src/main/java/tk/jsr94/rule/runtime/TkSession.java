package tk.jsr94.rule.runtime;

import java.util.List;
import java.util.Map;

/**
 * @author mengxr
 * @Package tk.jsr94.rule.runtime
 * @Description: session
 * @date 2018/11/28 下午12:57
 */
public interface TkSession {
    /**
     * execute expression
     *
     * @param rules
     * @param properties
     * @param list
     * @return
     */
    List executeExpression(List rules, Map properties, List list);
}
