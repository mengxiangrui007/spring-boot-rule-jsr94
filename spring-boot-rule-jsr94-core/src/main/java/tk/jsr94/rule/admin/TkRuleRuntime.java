package tk.jsr94.rule.admin;

import tk.jsr94.rule.runtime.TkStatelessRuleSession;

import javax.rules.*;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * @author mengxr
 * @Description: Tk RuleRuntime
 * @date 2018/11/23 下午7:57
 */
public class TkRuleRuntime implements RuleRuntime {
    private RuleExecutionSetRepository ruleExecutionSetRepository;

    public void setRuleExecutionSetRepository(RuleExecutionSetRepository ruleExecutionSetRepository) {
        this.ruleExecutionSetRepository = ruleExecutionSetRepository;
    }

    @Override
    public RuleSession createRuleSession(String uri, Map map, int ruleSessionType) throws RuleSessionTypeUnsupportedException, RuleSessionCreateException, RuleExecutionSetNotFoundException, RemoteException {
        if (ruleSessionType == RuleRuntime.STATELESS_SESSION_TYPE) {
            final TkStatelessRuleSession session = new TkStatelessRuleSession(ruleExecutionSetRepository,
                    uri, map);
            return session;
        }

        throw new RuleSessionTypeUnsupportedException("invalid session type: " + ruleSessionType);
    }

    @Override
    public List getRegistrations() throws RemoteException {
        try {
            return this.ruleExecutionSetRepository.getRegistrations();
        } catch (RuleExecutionSetRepositoryException e) {
            String s = "Error while retrieving list of registrations";
            throw new RuntimeException(s, e);
        }
    }
}
