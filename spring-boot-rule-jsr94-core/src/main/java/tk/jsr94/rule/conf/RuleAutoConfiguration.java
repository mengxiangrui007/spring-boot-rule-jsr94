package tk.jsr94.rule.conf;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.jsr94.rule.admin.*;
import tk.jsr94.rule.runtime.RuleTemplate;

import javax.rules.RuleRuntime;
import javax.rules.RuleServiceProvider;
import javax.rules.admin.LocalRuleExecutionSetProvider;
import javax.rules.admin.RuleAdministrator;

/**
 * @author mengxr
 * @Package tk.jsr94.rule.conf
 * @Description: Rule auto Configuration
 * @date 2018/11/28 下午1:31
 */
@Configuration
public class RuleAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(RuleServiceProvider.class)
    public RuleServiceProvider ruleServiceProvider(RuleAdministrator ruleAdministrator, RuleRuntime ruleRuntime) {
        TkRuleServiceProvider tkRuleServiceProvider = new TkRuleServiceProvider();
        tkRuleServiceProvider.setProviderClass(TkRuleServiceProvider.class);
        tkRuleServiceProvider.setProviderUri(TkRuleServiceProvider.RULE_SERVICE_PROVIDER);
        tkRuleServiceProvider.setRuleAdministrator(ruleAdministrator);
        tkRuleServiceProvider.setRuleRuntime(ruleRuntime);
        return tkRuleServiceProvider;
    }

    @Bean
    @ConditionalOnMissingBean(RuleSource.class)
    public RuleSource ruleSource(RuleServiceProvider ruleServiceProvider) {
        DefaultRuleSource ruleSource = new DefaultRuleSource();
        ruleSource.setRuleServiceProvider(ruleServiceProvider);
        return ruleSource;
    }

    @Bean
    @ConditionalOnMissingBean(RuleTemplate.class)
    public RuleTemplate ruleTemplate(RuleSource ruleSource) {
        return new RuleTemplate(ruleSource);
    }

    @Bean
    @ConditionalOnMissingBean(LocalRuleExecutionSetProvider.class)
    public LocalRuleExecutionSetProvider localRuleExecutionSetProvider() {
        return new TkLocalRuleExecutionSetProvider();
    }

    @Bean
    @ConditionalOnMissingBean(RuleAdministrator.class)
    public TkRuleAdministrator ruleAdministrator(LocalRuleExecutionSetProvider localRuleExecutionSetProvider, RuleExecutionSetRepository ruleExecutionSetRepository) {
        TkRuleAdministrator tkRuleAdministrator = new TkRuleAdministrator();
        tkRuleAdministrator.setLocalRuleExecutionSetProvider(localRuleExecutionSetProvider);
        tkRuleAdministrator.setRuleExecutionSetRepository(ruleExecutionSetRepository);
        return tkRuleAdministrator;
    }

    @Bean
    @ConditionalOnMissingBean(RuleRuntime.class)
    public TkRuleRuntime ruleRuntime(RuleExecutionSetRepository ruleExecutionSetRepository) {
        TkRuleRuntime tkRuleRuntime = new TkRuleRuntime();
        tkRuleRuntime.setRuleExecutionSetRepository(ruleExecutionSetRepository);
        return tkRuleRuntime;
    }

    @Bean
    @ConditionalOnMissingBean(RuleExecutionSetRepository.class)
    public RuleExecutionSetRepository ruleExecutionSetRepository() {
        return new DefaultRuleExecutionSetRepository();
    }
}
