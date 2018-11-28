package tk.jsr94.rule.admin;


import tk.jsr94.rule.language.RuleLanguage;

import javax.rules.admin.RuleExecutionSet;
import java.util.Collections;
import java.util.List;

/**
 * @author mengxr
 * @Package com.lianjia.matrix.marketing.rule.admin
 * @Description: Aviator RuleExecutionSet
 * @date 2018/11/23 下午8:12
 */
public class TkRuleExecutionSet implements RuleExecutionSet {
    private String rule;
    private RuleLanguage ruleLanguage;

    public TkRuleExecutionSet(String rule, RuleLanguage ruleLanguage) {
        this.rule = rule;
        this.ruleLanguage = ruleLanguage;
    }

    @Override
    public String getName() {
        return ruleLanguage.getName();
    }

    @Override
    public String getDescription() {
        return ruleLanguage.getDesc();
    }

    @Override
    public Object getProperty(Object o) {
        return null;
    }

    @Override
    public void setProperty(Object o, Object o1) {

    }

    @Override
    public String getDefaultObjectFilter() {
        return null;
    }

    @Override
    public void setDefaultObjectFilter(String s) {

    }

    @Override
    public List getRules() {
        return Collections.singletonList(rule);
    }
}
