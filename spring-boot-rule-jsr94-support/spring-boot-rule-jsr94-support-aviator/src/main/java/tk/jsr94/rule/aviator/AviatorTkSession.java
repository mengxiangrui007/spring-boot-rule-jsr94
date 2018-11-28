package tk.jsr94.rule.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import tk.jsr94.rule.runtime.TkSession;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author mengxr
 * @Package tk.jsr94.rule.aviator
 * @Description: aviator TkSession
 * @date 2018/11/28 下午1:15
 */
public class AviatorTkSession implements TkSession {
    /**
     * execute expression
     *
     * @param rules
     * @param properties
     * @param list
     * @return
     */
    @Override
    public List executeExpression(List rules, Map properties, List list) {
        String rule = (String) rules.get(0);
        Expression expression = AviatorEvaluator.compile(rule);
        Object execute = expression.execute(properties);
        return Collections.singletonList(execute);
    }
}
