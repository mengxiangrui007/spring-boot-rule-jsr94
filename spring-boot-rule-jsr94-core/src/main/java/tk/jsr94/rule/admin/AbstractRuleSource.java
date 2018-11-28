package tk.jsr94.rule.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import javax.rules.*;
import javax.rules.admin.RuleAdministrator;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * @author mengxr
 * @Description: Abstract Rule Source
 * @date 2018/11/23 下午4:57
 */
public abstract class AbstractRuleSource implements RuleSource, InitializingBean {
    /**
     * Shared final Log instance
     */
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractRuleSource.class);

    /**
     * RuleAdministrator instance
     */
    protected RuleAdministrator ruleAdministrator;

    /**
     * RuleRuntime instance
     */
    protected RuleRuntime ruleRuntime;

    /**
     * RuleServiceProvider implementation
     */
    private RuleServiceProvider ruleServiceProvider;

    public void setRuleServiceProvider(RuleServiceProvider ruleServiceProvider) {
        this.ruleServiceProvider = ruleServiceProvider;
    }

    /**
     * Invoked by a BeanFactory after it has set all bean properties supplied
     * (and satisfied BeanFactoryAware and ApplicationContextAware).
     * <p>This method allows the bean instance to perform initialization only
     * possible when all bean properties have been set and to throw an
     * exception in the event of misconfiguration.
     *
     * @throws Exception in the event of misconfiguration (such
     *                   as failure to set an essential property) or if initialization fails.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if (ruleServiceProvider != null && ruleAdministrator != null)
            throw new IllegalArgumentException("ruleServiceProvider and ruleAdministrator set on " + getClass().getName());
        if (ruleServiceProvider != null && ruleRuntime != null)
            throw new IllegalArgumentException("ruleServiceProvider and ruleRuntime set on " + getClass().getName());
        if (ruleServiceProvider != null /* && ruleAdministrator == null && ruleRuntime == null */) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Using ruleServiceProvider " + ruleServiceProvider.toString() + " to create ruleAdministrator and ruleRuntime.");
            }
            ruleAdministrator = ruleServiceProvider.getRuleAdministrator();
            ruleRuntime = ruleServiceProvider.getRuleRuntime();
        }
        if (ruleAdministrator == null)
            throw new IllegalArgumentException("Must set ruleAdministrator on " + getClass().getName());
        if (ruleRuntime == null) throw new IllegalArgumentException("Must set ruleRuntime on " + getClass().getName());
        registerRuleExecutionSets();
    }

    /**
     * Gets a RuleSession implementation for the specified bindUri, properties and type
     *
     * @param uri        The ruleset uri
     * @param properties The properties for the session
     * @param type       The session type
     * @return
     * @throws RuleExecutionException
     * @throws RemoteException
     */
    @Override
    public RuleSession createSession(String uri, Map<String, Object> properties, int type) throws RuleExecutionException, RemoteException {
        return ruleRuntime.createRuleSession(uri, properties, type);
    }

    protected abstract void registerRuleExecutionSets() throws RuleException, RemoteException, IOException;
}
