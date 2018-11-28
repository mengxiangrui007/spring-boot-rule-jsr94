package tk.jsr94.rule.admin;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.CollectionUtils;
import tk.jsr94.rule.language.RuleLanguage;
import tk.jsr94.rule.language.RuleLanguageProvider;

import javax.rules.RuleException;
import javax.rules.admin.LocalRuleExecutionSetProvider;
import javax.rules.admin.RuleExecutionSet;
import javax.rules.admin.RuleExecutionSetCreateException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.rmi.RemoteException;
import java.util.*;

/**
 * @author mengxr
 * @Description: default rule source implement ${@link RuleSource}
 * @date 2018/11/23 下午5:29
 */
public class DefaultRuleSource extends AbstractRuleSource implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * 获取利用反射获取类里面的值和名称
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        try {
            Class<?> clazz = obj.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(obj);
                map.put(fieldName, value);
            }
        } catch (IllegalAccessException e) {
            throw new ClassCastException("cast obj to map error");
        }
        return map;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    protected void registerRuleExecutionSets() throws RuleException, RemoteException, IOException {
        Set<RuleLanguage> ruleLanguageHashSet = getRuleLanguage();
        Iterator<RuleLanguage> iterator = ruleLanguageHashSet.iterator();
        RuleLanguage ruleLanguage = null;
        LocalRuleExecutionSetProvider localRuleExecutionSetProvider = null;
        Map<String, Object> ruleLanguageMaps = null;
        while (iterator.hasNext()) {
            ruleLanguage = iterator.next();
            ruleLanguageMaps = objectToMap(ruleLanguage);
            localRuleExecutionSetProvider = ruleAdministrator.getLocalRuleExecutionSetProvider(ruleLanguageMaps);
            RuleExecutionSet ruleSet = null;
            try {
                ruleSet = localRuleExecutionSetProvider.createRuleExecutionSet(ruleLanguage.getContent(), ruleLanguageMaps);
            } catch (RuleExecutionSetCreateException e) {
                e.printStackTrace();
            }
            ruleAdministrator.registerRuleExecutionSet(ruleLanguage.getCode(), ruleSet, ruleLanguageMaps);
        }
    }

    /**
     * get rule language
     *
     * @return
     */
    private Set<RuleLanguage> getRuleLanguage() {
        Set<RuleLanguage> ruleLanguageHashSet = new HashSet<>();
        String[] beanNames = applicationContext.getBeanNamesForType(RuleLanguageProvider.class);
        if (beanNames != null && beanNames.length > 0) {
            for (String beanName : beanNames) {
                RuleLanguageProvider ruleLanguageProvider = applicationContext.getBean(beanName, RuleLanguageProvider.class);
                Set<RuleLanguage> ruleLanguages = ruleLanguageProvider.registerRuleLanguageSet();
                if (!CollectionUtils.isEmpty(ruleLanguages)) {
                    ruleLanguageHashSet.addAll(ruleLanguages);
                }
            }
        }
        return ruleLanguageHashSet;
    }
}
