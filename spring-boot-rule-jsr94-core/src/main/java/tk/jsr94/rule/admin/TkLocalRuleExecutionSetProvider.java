package tk.jsr94.rule.admin;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import tk.jsr94.rule.language.RuleLanguage;

import javax.rules.admin.LocalRuleExecutionSetProvider;
import javax.rules.admin.RuleExecutionSet;
import javax.rules.admin.RuleExecutionSetCreateException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Map;

/**
 * @author mengxr
 * @Description: LocalRuleExecutionSetProvider
 * @date 2018/11/23 下午8:09
 */
public class TkLocalRuleExecutionSetProvider implements LocalRuleExecutionSetProvider {
    @Override
    public RuleExecutionSet createRuleExecutionSet(InputStream inputStream, Map map) throws RuleExecutionSetCreateException, IOException {
        return null;
    }

    @Override
    public RuleExecutionSet createRuleExecutionSet(Reader reader, Map map) throws RuleExecutionSetCreateException, IOException {
        return null;
    }

    @Override
    public RuleExecutionSet createRuleExecutionSet(Object o, Map map) throws RuleExecutionSetCreateException {
        RuleLanguage ruleLanguage = Jackson2ObjectMapperBuilder.json().build().convertValue(map, RuleLanguage.class);
        Jackson2ObjectMapperBuilder json = Jackson2ObjectMapperBuilder.json();
        return new TkRuleExecutionSet((String) o, ruleLanguage);
    }
}
