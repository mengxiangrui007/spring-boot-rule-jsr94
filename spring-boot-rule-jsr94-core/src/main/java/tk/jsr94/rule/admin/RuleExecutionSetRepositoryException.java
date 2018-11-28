package tk.jsr94.rule.admin;

import javax.rules.RuleExecutionException;

/**
 * @author mengxr
 * @Description: Rule  Execution Set Repository
 * @date 2018/11/26 上午1:28
 */
public class RuleExecutionSetRepositoryException extends RuleExecutionException {
    public RuleExecutionSetRepositoryException(String message) {
        super(message);
    }

    public RuleExecutionSetRepositoryException(String message, Exception exception) {
        super(message, exception);
    }
}
