package org.iceberg.script;

public class CityRuleDetail {
	
    private Long id;

    private Long ruleId;
    /**
     * 规则种类
     */
    private int ruleType;
    /**
     * 服务类型
     */
    private int serviceType;
    /**
     * 状态
     */
    private int status;
    /**
     * 起步价
     */
    private Integer startPrice;
    /**
     * 起步里程
     */
    private Double startIncludeKm;
    /**
     * 起步分钟
     */
    private Integer startIncludeMinute;
    /**
     * 里程费
     */
    private Integer kmPrice;
    /**
     * 时长单价
     */
    private Integer timePrice;
    /**
     * 长途里程
     */
    private Double longWay;
    /**
     * 长途单价
     */
    private Integer longWayPrice;
    /**
     * 夜间开始区间
     */
    private String nightStart;
    /**
     * 夜间结束区间
     */
    private String nightEnd;
    /**
     * 夜间起步里程
     */
    private Double nightIncludeKm;
    /**
     * 夜间起步价格
     */
    private Integer nightStartPrice;
    /**
     * 夜间里程价格
     */
    private Integer nightPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public int getRuleType() {
        return ruleType;
    }

    public void setRuleType(int ruleType) {
        this.ruleType = ruleType;
    }

    public int getServiceType() {
        return serviceType;
    }

    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Integer startPrice) {
        this.startPrice = startPrice;
    }

    public Double getStartIncludeKm() {
        return startIncludeKm;
    }

    public void setStartIncludeKm(Double startIncludeKm) {
        this.startIncludeKm = startIncludeKm;
    }

    public Integer getStartIncludeMinute() {
        return startIncludeMinute;
    }

    public void setStartIncludeMinute(Integer startIncludeMinute) {
        this.startIncludeMinute = startIncludeMinute;
    }

    public Integer getKmPrice() {
        return kmPrice;
    }

    public void setKmPrice(Integer kmPrice) {
        this.kmPrice = kmPrice;
    }

    public Integer getTimePrice() {
        return timePrice;
    }

    public void setTimePrice(Integer timePrice) {
        this.timePrice = timePrice;
    }

    public Double getLongWay() {
        return longWay;
    }

    public void setLongWay(Double longWay) {
        this.longWay = longWay;
    }

    public Integer getLongWayPrice() {
        return longWayPrice;
    }

    public void setLongWayPrice(Integer longWayPrice) {
        this.longWayPrice = longWayPrice;
    }

    public String getNightStart() {
        return nightStart;
    }

    public void setNightStart(String nightStart) {
        this.nightStart = nightStart;
    }

    public String getNightEnd() {
        return nightEnd;
    }

    public void setNightEnd(String nightEnd) {
        this.nightEnd = nightEnd;
    }

    public Double getNightIncludeKm() {
        return nightIncludeKm;
    }

    public void setNightIncludeKm(Double nightIncludeKm) {
        this.nightIncludeKm = nightIncludeKm;
    }

    public Integer getNightStartPrice() {
        return nightStartPrice;
    }

    public void setNightStartPrice(Integer nightStartPrice) {
        this.nightStartPrice = nightStartPrice;
    }

    public Integer getNightPrice() {
        return nightPrice;
    }

    public void setNightPrice(Integer nightPrice) {
        this.nightPrice = nightPrice;
    }
}