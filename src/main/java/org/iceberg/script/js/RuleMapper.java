package org.iceberg.script.js;

import com.alibaba.fastjson.JSON;

import org.iceberg.script.CityRuleDetail;

import java.io.FileReader;
import java.net.URL;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Created by xiaoyuzhzh on 6/14/2018.
 */
public class RuleMapper {




    public static void main(String[] args) throws Exception {
        String ruleSnap = "{\"@type\":\"com.caocao.billing.server.evolution.dao.entity.detail.TravelRuleListPO\",\"travel\":[{\"action\":{\"actionType\":\"KM\",\"interval\":{\"a\":\"MIDDLE\",\"l\":\"0.0\",\"lb\":\"CLOSED\",\"u\":\"3.0\",\"ub\":\"OPEN\"}},\"category\":1,\"condition\":{\"conditionType\":\"DEFAULT\"},\"name\":\"起步里程费\",\"price\":{\"price\":1000,\"priceType\":\"FEE\",\"unit\":1.0}},{\"action\":{\"actionType\":\"KM\",\"interval\":{\"a\":\"MIDDLE\",\"l\":\"3.0\",\"lb\":\"CLOSED\",\"u\":\"1.7976931348623157E308\",\"ub\":\"OPEN\"}},\"category\":3,\"condition\":{\"conditionType\":\"DEFAULT\"},\"name\":\"里程费\",\"price\":{\"price\":250,\"priceType\":\"UNIT\",\"unit\":1.0}},{\"action\":{\"actionType\":\"MIN\",\"interval\":{\"a\":\"MIDDLE\",\"l\":\"0\",\"lb\":\"CLOSED\",\"u\":\"2147483647\",\"ub\":\"OPEN\"}},\"category\":4,\"condition\":{\"conditionType\":\"DEFAULT\"},\"name\":\"时长费\",\"price\":{\"price\":30,\"priceType\":\"UNIT\",\"unit\":1.0}},{\"action\":{\"actionType\":\"KM\",\"interval\":{\"a\":\"MIDDLE\",\"l\":\"15.0\",\"lb\":\"CLOSED\",\"u\":\"1.7976931348623157E308\",\"ub\":\"OPEN\"}},\"category\":5,\"condition\":{\"conditionType\":\"DEFAULT\"},\"name\":\"长途费\",\"price\":{\"price\":125,\"priceType\":\"UNIT\",\"unit\":1.0}},{\"action\":{\"actionType\":\"KM\",\"interval\":{\"a\":\"MIDDLE\",\"l\":\"0.0\",\"lb\":\"CLOSED\",\"u\":\"5.0\",\"ub\":\"OPEN\"}},\"category\":7,\"condition\":{\"conditionType\":\"TIME\",\"intervalList\":[{\"a\":\"MIDDLE\",\"l\":\"23:00:00\",\"lb\":\"CLOSED\",\"u\":\"05:00:00\",\"ub\":\"OPEN\"}]},\"name\":\"夜间起步里程费\",\"price\":{\"price\":200,\"priceType\":\"FEE\",\"unit\":1.0}},{\"action\":{\"actionType\":\"KM\",\"interval\":{\"a\":\"MIDDLE\",\"l\":\"5.0\",\"lb\":\"CLOSED\",\"u\":\"1.7976931348623157E308\",\"ub\":\"OPEN\"}},\"category\":8,\"condition\":{\"conditionType\":\"TIME\",\"intervalList\":[{\"a\":\"MIDDLE\",\"l\":\"23:00:00\",\"lb\":\"CLOSED\",\"u\":\"05:00:00\",\"ub\":\"OPEN\"}]},\"name\":\"夜间里程费\",\"price\":{\"price\":100,\"priceType\":\"UNIT\",\"unit\":1.0}},{\"action\":{\"actionType\":\"MIN\",\"interval\":{\"a\":\"MIDDLE\",\"l\":\"0\",\"lb\":\"CLOSED\",\"u\":\"2147483647\",\"ub\":\"OPEN\"}},\"category\":9,\"condition\":{\"conditionType\":\"TIME\",\"intervalList\":[{\"a\":\"MIDDLE\",\"l\":\"23:00:00\",\"lb\":\"CLOSED\",\"u\":\"05:00:00\",\"ub\":\"OPEN\"}]},\"name\":\"夜间时长费\",\"price\":{\"price\":0,\"priceType\":\"UNIT\",\"unit\":1.0}}]}";

        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        String resource = RuleMapper.class.getResource("/").toString();
        String base = resource.replace("file:", "");
        engine.eval(new FileReader(base+"jsscript/ruleMapper.js"));
        Invocable invocable = (Invocable) engine;
        CityRuleDetail ruleMapper = (CityRuleDetail) invocable.invokeFunction("ruleMapper", ruleSnap);
        System.out.println(JSON.toJSONString(ruleMapper,true));
    }
}
