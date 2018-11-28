package tk.jsr94.rule.language;

import java.util.Set;

/**
 * @author mengxr
 * @Package com.lianjia.matrix.marketing.rule.language
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
