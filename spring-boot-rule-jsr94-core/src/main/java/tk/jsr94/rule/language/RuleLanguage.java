package tk.jsr94.rule.language;

import java.util.Objects;

/**
 * @author mengxr
 * @Description: rule language
 * @date 2018/11/23 下午5:26
 */
public class RuleLanguage {
    /**
     * rule Code
     */
    private String code;
    /**
     * rule Type
     */
    private String type;
    /**
     * rule Name
     */
    private String name;
    /**
     * rule text
     */
    private String content;
    /**
     * rule desc
     */
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuleLanguage that = (RuleLanguage) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, type);
    }
}
