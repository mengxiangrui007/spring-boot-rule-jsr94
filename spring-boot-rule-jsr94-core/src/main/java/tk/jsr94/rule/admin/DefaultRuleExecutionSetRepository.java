package tk.jsr94.rule.admin;

import javax.rules.admin.RuleExecutionSet;
import javax.rules.admin.RuleExecutionSetDeregistrationException;
import javax.rules.admin.RuleExecutionSetRegisterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mengxr
 * @Description: default cache impl {@link RuleExecutionSetRepository}
 * @date 2018/11/26 上午1:30
 */
public class DefaultRuleExecutionSetRepository implements RuleExecutionSetRepository {
    /**
     * JVM RuleExecutionSet cache
     */
    private Map<String, RuleExecutionSet> ruleExecutionSetConcurrentMap = new ConcurrentHashMap<>();

    /**
     * Retrieves a <code>List</code> of the URIs that currently have
     * <code>RuleExecutionSet</code>s associated with them.
     * <p>
     * An empty list is returned if there are no associations.
     *
     * @return a <code>List</code> of the URIs that currently have
     * <code>RuleExecutionSet</code>s associated with them.
     * @throws RuleExecutionSetRepositoryException
     */
    @Override

    public List getRegistrations() throws RuleExecutionSetRepositoryException {
        return new ArrayList(ruleExecutionSetConcurrentMap.keySet());
    }

    /**
     * Get the <code>RuleExecutionSet</code> bound to this URI, or return
     * <code>null</code>.
     *
     * @param bindUri    the URI associated with the wanted
     *                   <code>RuleExecutionSet</code>.
     * @param properties
     * @return the <code>RuleExecutionSet</code> bound to the given URI.
     * @throws RuleExecutionSetRepositoryException
     */
    @Override
    public RuleExecutionSet getRuleExecutionSet(String bindUri, Map properties) throws RuleExecutionSetRepositoryException {
        return ruleExecutionSetConcurrentMap.get(bindUri);
    }

    /**
     * Register a <code>RuleExecutionSet</code> under the given URI.
     *
     * @param bindUri    the URI to associate with the <code>RuleExecutionSet</code>.
     * @param ruleSet    the <code>RuleExecutionSet</code> to associate with the URI
     * @param properties
     * @throws RuleExecutionSetRegisterException if an error occurred that prevented registration (i.e. if
     *                                           bindUri or ruleSet are <code>null</code>)
     */
    @Override
    public void registerRuleExecutionSet(String bindUri, RuleExecutionSet ruleSet, Map properties) throws RuleExecutionSetRegisterException {
        if (bindUri == null) {
            throw new RuleExecutionSetRegisterException("bindUri cannot be null");
        }
        if (ruleSet == null) {
            throw new RuleExecutionSetRegisterException("ruleSet cannot be null");
        }
        ruleExecutionSetConcurrentMap.put(bindUri, ruleSet);
    }

    /**
     * Unregister a <code>RuleExecutionSet</code> from the given URI.
     *
     * @param bindUri    the URI to disassociate with the <code>RuleExecutionSet</code>.
     * @param properties
     * @throws RuleExecutionSetDeregistrationException if an error occurred that prevented deregistration
     */
    @Override
    public void unregisterRuleExecutionSet(String bindUri, Map properties) throws RuleExecutionSetDeregistrationException {
        if (bindUri == null) {
            throw new RuleExecutionSetDeregistrationException("bindUri cannot be null");
        }
        ruleExecutionSetConcurrentMap.remove(bindUri);
    }
}
