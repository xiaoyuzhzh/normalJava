var simpleRuleMapper = [];

//转换起步费
function setStartFee(rule,snap){
    snap.startIncludeKm = rule.action.interval.u;
    snap.startPrice = rule.price.price;
}
simpleRuleMapper[1] = setStartFee;

//转换里程费
function setKmFee(rule,snap){
    snap.kmPrice = rule.price.price;
}
simpleRuleMapper[3] = setKmFee;

//转换时长费
function setMinuteFee(rule,snap){
    snap.startIncludeMinute = rule.action.interval.l;
    snap.timePrice = rule.price.price;
}
simpleRuleMapper[4] = setMinuteFee;

//转换长途费
function setLongWay(rule,snap){
    snap.longWay = rule.action.interval.l;
    snap.longWayPrice = rule.price.price;
}
simpleRuleMapper[5] = setLongWay;

//夜间起步里程费
function setNightStart(rule,snap){
    snap.nightStart = rule.condition.intervalList[0].l;
    snap.nightEnd = rule.condition.intervalList[0].u;
    snap.nightIncludeKm = rule.action.interval.u;
    snap.nightStartPrice = rule.price.price;
}
simpleRuleMapper[7] = setNightStart;

//夜间里程费
function setNightKm(rule,snap){
    snap.nightPrice = rule.price.price;
}
simpleRuleMapper[8] = setNightKm;



//计费规则快照转换器
var ruleMapper = function(ruleStr){
    var rule = eval('(' + ruleStr + ')');

    var travelRules = rule.travel;


    var ruleSnapType = Java.type('org.iceberg.script.CityRuleDetail');
    var ruleSnap = new ruleSnapType();
    travelRules.forEach(function(simple){
        var mapper = simpleRuleMapper[simple.category]
        if(mapper != null && mapper != undefined){
            mapper.call(this,simple,ruleSnap);
        }
    });


    return ruleSnap;
}