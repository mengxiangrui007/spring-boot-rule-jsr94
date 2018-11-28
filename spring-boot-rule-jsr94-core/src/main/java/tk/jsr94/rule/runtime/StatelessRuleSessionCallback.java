package tk.jsr94.rule.runtime;

import javax.rules.InvalidRuleSessionException;
import javax.rules.StatelessRuleSession;
import java.rmi.RemoteException;

/**
 * @author mengxr
 * @Description: StatelessRuleSession not save state
 * @date 2018/11/23 下午6:39
 */
public interface StatelessRuleSessionCallback {
    /**
     * Perform operations on the session.
     *
     * @param session The StatelessRuleSession
     * @return Any value
     * @throws InvalidRuleSessionException If the session is invalid
     * @throws RemoteException             If the remote call fails
     */
    Object execute(StatelessRuleSession session) throws InvalidRuleSessionException, RemoteException;
}
