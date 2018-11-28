package tk.jsr94.rule.runtime;

import org.springframework.beans.factory.InitializingBean;
import tk.jsr94.rule.admin.RuleSource;

/**
 * @author mengxr
 * @Description: Rule Accessor
 * @date 2018/11/23 下午6:47
 */
public abstract class RuleAccessor implements InitializingBean {
    /**
     * The ruleSource instance
     */
    protected RuleSource ruleSource;

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() {
        if (ruleSource == null) throw new IllegalArgumentException("Must set ruleSource on " + getClass().getName());
    }

    /**
     * Gets the value of ruleSource
     *
     * @return Value of ruleSource.
     */
    protected final RuleSource getRuleSource() {
        return ruleSource;
    }

    /**
     * Sets new value for field ruleSource
     *
     * @param ruleSource The ruleSource to set.
     */
    protected final void setRuleSource(RuleSource ruleSource) {
        this.ruleSource = ruleSource;
    }

}
