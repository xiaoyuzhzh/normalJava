var ruleSnapType = Java.type('org.iceberg.script.CityRuleDetail');//出参正常不会变，可以使用这个，不然就用map，正常不会直接返回json对象（java拿到的json对象会被包装成ScriptObjectMirror）
var FeeType = Java.type('com.caocao.rule.enums.FeeType');//使用枚举可能不太好，插件可以直接升级，枚举需要升级jar


var simpleRuleMapper = [];

//转换起步费
function setStartFee(rule,snap){
    snap.startIncludeKm = rule.action.interval.u;
    snap.startPrice = rule.price.price;
}
simpleRuleMapper[FeeType.START_KM_FEE.code()] = setStartFee;

//转换里程费
function setKmFee(rule,snap){
    snap.kmPrice = rule.price.price;
}
simpleRuleMapper[FeeType.KM_FEE.code()] = setKmFee;

//转换时长费
function setMinuteFee(rule,snap){
    snap.startIncludeMinute = rule.action.interval.l;
    snap.timePrice = rule.price.price;
}
simpleRuleMapper[FeeType.MINUTE_FEE.code()] = setMinuteFee;

//转换长途费
function setLongWay(rule,snap){
    snap.longWay = rule.action.interval.l;
    snap.longWayPrice = rule.price.price;
}
simpleRuleMapper[FeeType.LONG_KM_FEE.code()] = setLongWay;

//夜间起步里程费
function setNightStart(rule,snap){
    snap.nightStart = rule.condition.intervalList[0].l;
    snap.nightEnd = rule.condition.intervalList[0].u;
    snap.nightIncludeKm = rule.action.interval.u;
    snap.nightStartPrice = rule.price.price;
}
simpleRuleMapper[FeeType.NIGHT_START_KM_FEE.code()] = setNightStart;

//夜间里程费
function setNightKm(rule,snap){
    snap.nightPrice = rule.price.price;
}
simpleRuleMapper[FeeType.NIGHT_KM_FEE.code()] = setNightKm;



//计费规则快照转换器
var ruleMapper = function(ruleStr){
    var rule = eval('(' + ruleStr + ')');

    var travelRules = rule.travel;
    var ruleSnap = new ruleSnapType();
    travelRules.forEach(function(simple){
        var mapper = simpleRuleMapper[simple.category]
        if(mapper != null && mapper != undefined){
            mapper.call(this,simple,ruleSnap);
        }
    });


    return ruleSnap;
}