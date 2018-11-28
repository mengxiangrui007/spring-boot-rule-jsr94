package tk.jsr94.rule.admin;

import javax.rules.admin.*;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * @author mengxr
 * @Description: Tk RuleAdministrator
 * @date 2018/11/23 下午7:47
 */
public class TkRuleAdministrator implements RuleAdministrator {
    private LocalRuleExecutionSetProvider localRuleExecutionSetProvider;

    private RuleExecutionSetRepository ruleExecutionSetRepository;

    public void setRuleExecutionSetRepository(RuleExecutionSetRepository ruleExecutionSetRepository) {
        this.ruleExecutionSetRepository = ruleExecutionSetRepository;
    }

    public void setLocalRuleExecutionSetProvider(LocalRuleExecutionSetProvider localRuleExecutionSetProvider) {
        this.localRuleExecutionSetProvider = localRuleExecutionSetProvider;
    }


    @Override
    public LocalRuleExecutionSetProvider getLocalRuleExecutionSetProvider(Map map) throws RemoteException {
        return localRuleExecutionSetProvider;
    }

    @Override
    public RuleExecutionSetProvider getRuleExecutionSetProvider(Map map) throws RemoteException {
        return null;
    }

    @Override
    public void registerRuleExecutionSet(String s, RuleExecutionSet ruleExecutionSet, Map map) throws RuleExecutionSetRegisterException, RemoteException {
        ruleExecutionSetRepository.registerRuleExecutionSet(s, ruleExecutionSet, map);
    }

    @Override
    public void deregisterRuleExecutionSet(String s, Map map) throws RuleExecutionSetDeregistrationException, RemoteException {

        try {
            if (this.ruleExecutionSetRepository.getRuleExecutionSet(s, map) == null) {
                throw new RuleExecutionSetDeregistrationException("no execution set bound to: " + map);
            }
        } catch (RuleExecutionSetRepositoryException e) {
            String error = "Error while retrieving rule execution set bound to: " + s;
            throw new RuleExecutionSetDeregistrationException(error, e);
        }

        ruleExecutionSetRepository.unregisterRuleExecutionSet(s, map);
    }
}
