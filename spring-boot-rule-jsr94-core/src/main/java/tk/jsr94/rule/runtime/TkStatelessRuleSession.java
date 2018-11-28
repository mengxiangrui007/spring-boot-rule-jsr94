package tk.jsr94.rule.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.jsr94.rule.admin.RuleExecutionSetRepository;
import tk.jsr94.rule.admin.RuleExecutionSetRepositoryException;
import tk.jsr94.rule.admin.TkRuleExecutionSet;

import javax.rules.*;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author mengxr
 * @Description: Avivator StatelessRuleSession
 * @date 2018/11/26 上午1:53
 */
public class TkStatelessRuleSession implements StatelessRuleSession {
    /**
     * Shared final Log instance
     */
    protected static final Logger LOGGER = LoggerFactory.getLogger(TkStatelessRuleSession.class);
    private RuleExecutionSetRepository ruleExecutionSetRepository;
    private String bindUri;
    private Map properties;
    private TkRuleExecutionSet tkStatelessRuleSession;
    private TkSession tkSession;

    public TkStatelessRuleSession(RuleExecutionSetRepository ruleExecutionSetRepository, String bindUri, Map properties) {
        this.ruleExecutionSetRepository = ruleExecutionSetRepository;
        this.bindUri = bindUri;
        this.properties = properties;
        init(bindUri, properties);
    }

    /**
     * init
     *
     * @param bindUri
     * @param properties
     */
    private void init(String bindUri, Map properties) {
        try {
            tkStatelessRuleSession = (TkRuleExecutionSet) ruleExecutionSetRepository.getRuleExecutionSet(bindUri, properties);
            ServiceLoader<TkSession> tkSessionServiceLoader = ServiceLoader.load(TkSession.class);
            for (TkSession tkSessionService : tkSessionServiceLoader) {
                tkSession = tkSessionService;
            }
        } catch (RuleExecutionSetRepositoryException e) {
            String s = "Error while retrieving rule execution set bound to: " + bindUri;
            throw new RuleSessionCreateException(s, e);
        }
    }


    @Override
    public List executeRules(List list) throws InvalidRuleSessionException, RemoteException {
        List rules = tkStatelessRuleSession.getRules();
        List rulesResults = tkSession.executeExpression(rules, properties, list);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Execute rule engine bindUri[{}] rules[{}] rulesResults[{}]", bindUri, rules, rulesResults);
        }
        return rulesResults;
    }

    @Override
    public List executeRules(List list, ObjectFilter objectFilter) throws InvalidRuleSessionException, RemoteException {
        return null;
    }

    @Override
    public RuleExecutionSetMetadata getRuleExecutionSetMetadata() throws InvalidRuleSessionException, RemoteException {
        return null;
    }

    @Override
    public void release() throws RemoteException, InvalidRuleSessionException {
        tkStatelessRuleSession = null;
        bindUri = null;
        properties = null;
    }

    @Override
    public int getType() throws RemoteException, InvalidRuleSessionException {
        return RuleRuntime.STATELESS_SESSION_TYPE;
    }
}
