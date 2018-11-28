package tk.jsr94.rule.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.jsr94.rule.RuleRunTimeException;
import tk.jsr94.rule.admin.RuleSource;

import javax.rules.RuleRuntime;
import javax.rules.RuleSession;
import javax.rules.StatelessRuleSession;
import java.util.Map;

/**
 * @author mengxr
 * @Description: rule template class
 * @date 2018/11/23 下午6:46
 */
public class RuleTemplate extends RuleAccessor {
    /**
     * Shared final Log instance
     */
    protected static final Logger LOGGER = LoggerFactory.getLogger(RuleTemplate.class);

    public RuleTemplate() {
    }

    public RuleTemplate(RuleSource ruleSource) {
        setRuleSource(ruleSource);
    }

    /**
     * Creates instance of RuleSession, maps the checked exceptions and returns a valid RuleSession
     *
     * @param uri        The uri of the session
     * @param properties The properties for the session
     * @param type       The type of the session
     * @return RuleSession instance
     */
    private RuleSession createRuleSession(final String uri, final Map properties, final int type) {
        try {
            return getRuleSource().createSession(uri, properties, type);
        } catch (Exception e) {
            throw new RuleRunTimeException(e);
        }
    }

    /**
     * Releases a stateless session
     *
     * @param session The session to be released
     */
    private void releaseSession(final RuleSession session) {
        if (session == null) throw new IllegalArgumentException("session must not be null");
        try {
            session.release();
        } catch (Exception e) {
            throw new RuleRunTimeException(e);
        }
    }

    /**
     * Executes a stateless rule session
     *
     * @param uri        The ruleset uri
     * @param properties The proeprties for the session
     * @param callback   The executor callback
     * @return Value returned by the executor implementation
     */
    public Object executeStateless(final String uri, final Map<String, String> properties, final StatelessRuleSessionCallback callback) {
        StatelessRuleSession session = (StatelessRuleSession) createRuleSession(uri, properties, RuleRuntime.STATELESS_SESSION_TYPE);
        try {
            return callback.execute(session);
        } catch (Exception e) {
            throw new RuleRunTimeException(e);
        } finally {
            releaseSession(session);
        }
    }

    /**
     * Executes a stateful rule session. If called in a transaction, the session will be closed upon
     * transaction completion; otherwise it will be closed when the callback returns.
     *
     * @param uri        The ruleset uri
     * @param properties The properties for the stateful rule session
     * @param callback   The callback
     * @return The result of the callback execution
     */
    public Object executeStateful(final String uri, final Map<String, String> properties, final StatefulRuleSessionCallback callback) {
        return null;
    }
}
