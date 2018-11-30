package tk.jsr94.rule.language;

import java.util.Set;

/**
 * @author mengxr
 * @Description: RuleLanguage RuleLanguageProvider
 * @date 2018/11/23 下午5:43
 */
public interface RuleLanguageProvider {
    /**
     * register rule language
     *
     * @return
     */
    Set<RuleLanguage> registerRuleLanguageSet();
}
