function find(cityRuleDetail){
    var RuleFinder = Java.type("org.iceberg.script.js.RuleFinder");
    var rule = RuleFinder.findRule(1.1);
    print(cityRuleDetail.ruleId);
}