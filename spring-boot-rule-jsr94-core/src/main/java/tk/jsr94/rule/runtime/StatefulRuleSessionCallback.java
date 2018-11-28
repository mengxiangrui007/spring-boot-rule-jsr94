package tk.jsr94.rule.runtime;

import javax.rules.InvalidHandleException;
import javax.rules.InvalidRuleSessionException;
import javax.rules.StatefulRuleSession;
import java.rmi.RemoteException;

/**
 * @author mengxr
 * @Description: StatefulRuleSession Working Memory save state
 * @date 2018/11/23 下午6:40
 */
public interface StatefulRuleSessionCallback {
    /**
     * Perform operations on the session.
     *
     * @return Any value
     * @throws InvalidRuleSessionException If the session is invalid
     * @throws RemoteException             If the remote call fails
     */
    Object execute(StatefulRuleSession session) throws InvalidRuleSessionException, InvalidHandleException, RemoteException;
}
