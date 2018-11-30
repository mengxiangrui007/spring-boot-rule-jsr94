# spring-boot-rule-jsr94
jsr94  link https://github.com/astubbs/spring-modules.git
# 规则引擎

## 设计规范
Spring boot封装，扩展实现java规范[Jsr94](https://www.jcp.org/ja/jsr/detail?id=94)

## 支持组件

### Aviator支持

```
  <dependency>
          <groupId>tk.jsr94</groupId>
          <artifactId>spring-boot-rule-jsr94-starter-aviator</artifactId>
          <version>1.0.0-SNAPSHOT</version>
   </dependency>
```
## 使用说明

### 实现规则加载接口加载规则定义
``` java
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

```
### 使用规则

``` java
    @Autowired
    private RuleTemplate ruleTemplate;
    
    HashMap<String, String> envs = new HashMap<>(1);
    ruleTemplate.executeStateless(规则编码, envs,
                session -> session.executeRules(Collections.emptyList()).get(0));
```
