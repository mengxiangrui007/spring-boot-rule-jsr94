package tk.jsr94.rule.admin;

import javax.rules.RuleExecutionException;
import javax.rules.RuleSession;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * @author mengxr
 * @Description: rule source
 * @date 2018/11/23 下午4:53
 */
public interface RuleSource {
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
    RuleSession createSession(String uri, Map<String, Object> properties, int type) throws RuleExecutionException, RemoteException;

    ;
}
