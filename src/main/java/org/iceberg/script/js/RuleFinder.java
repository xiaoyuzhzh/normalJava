package org.iceberg.script.js;

import org.iceberg.script.CityRuleDetail;

import javax.script.Invocable;
import javax.script.ScriptEngine;

import static org.iceberg.script.js.RuleMapper.getScriptEngine;

/**
 * Created by xiaoyuzhzh on 6/14/2018.
 */
public class RuleFinder {

    public static void main(String[] args) throws Exception {
        ScriptEngine engine = getScriptEngine("jsscript/ruleFinder.js");
        Invocable invocable = (Invocable) engine;
        CityRuleDetail cityRuleDetail = new CityRuleDetail();
        cityRuleDetail.setRuleId(3l);
        Object find = invocable.invokeFunction("find", cityRuleDetail);
//        System.out.println(find);
    }

    public static CityRuleDetail findRule(Long ruleId){
        CityRuleDetail cityRuleDetail = new CityRuleDetail();
        cityRuleDetail.setRuleId(ruleId);

        return cityRuleDetail;
    }
}
