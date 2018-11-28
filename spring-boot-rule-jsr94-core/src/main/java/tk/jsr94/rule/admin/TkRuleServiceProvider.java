package tk.jsr94.rule.admin;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.rules.ConfigurationException;
import javax.rules.RuleRuntime;
import javax.rules.RuleServiceProvider;
import javax.rules.RuleServiceProviderManager;
import javax.rules.admin.RuleAdministrator;

/**
 * @author mengxr
 * @Description: RuleServiceProvider
 * @date 2018/11/23 下午7:15
 */
public class TkRuleServiceProvider extends RuleServiceProvider implements InitializingBean, DisposableBean {
    public final static String RULE_SERVICE_PROVIDER = "tk.rule.engine";
    private RuleRuntime ruleRuntime;
    private RuleAdministrator ruleAdministrator;
    /**
     * provider Uri
     */
    private String providerUri;
    /**
     * provider Class
     */
    private Class providerClass;

    @Override
    public void afterPropertiesSet() throws Exception {
        // check the arguments
        if (providerUri == null) {
            throw new IllegalArgumentException("Must set providerUri on " + getClass().getName());
        }
        if (providerClass == null) {
            throw new IllegalArgumentException("Must set providerClass on " + getClass().getName());
        }
        RuleServiceProviderManager.registerRuleServiceProvider(providerUri, providerClass);
    }

    @Override
    public void destroy() throws Exception {
        RuleServiceProviderManager.deregisterRuleServiceProvider(providerUri);
    }

    public void setProviderUri(String providerUri) {
        this.providerUri = providerUri;
    }

    public void setProviderClass(Class providerClass) {
        this.providerClass = providerClass;
    }

    @Override
    public RuleRuntime getRuleRuntime() throws ConfigurationException {
        return this.ruleRuntime;
    }

    public void setRuleRuntime(RuleRuntime ruleRuntime) {
        this.ruleRuntime = ruleRuntime;
    }

    @Override
    public RuleAdministrator getRuleAdministrator() throws ConfigurationException {
        return ruleAdministrator;
    }

    public void setRuleAdministrator(RuleAdministrator ruleAdministrator) {
        this.ruleAdministrator = ruleAdministrator;
    }

}
