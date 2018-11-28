package tk.jsr94.rule;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

/**
 * @author mengxr
 * @Description: rule Failure Analyzer
 * @date 2018/11/23 下午7:39
 */
public class RuleFailureAnalyzer extends AbstractFailureAnalyzer<RuleRunTimeException> {
    /**
     * Returns an analysis of the given {@code failure}, or {@code null} if no analysis
     * was possible.
     *
     * @param rootFailure the root failure passed to the analyzer
     * @param cause       the actual found cause
     * @return the analysis or {@code null}
     */
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, RuleRunTimeException cause) {
        return new FailureAnalysis(
                "RULE engine failed ", " rule ", cause);
    }
}
