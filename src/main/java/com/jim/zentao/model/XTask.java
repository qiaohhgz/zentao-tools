package com.jim.zentao.model;

/**
 * @author jim乔仕举
 * @version V1.0
 * @Title: XTask.java
 * @Package com.jim.zentao.model
 * @Description: excl task
 * @date 2016年03月15日 下午5:55
 */
public class XTask {
    private String project;//所属项目
    private String module;//所属模块
    private String name;//任务名称
    private String desc;//任务描述
    private String type;//任务类型
    private String relNeeds;//相关需求
    private String priority;//优先级
    private String firstExpect;//最初预计
    private String expectStartTime;//预计开始
    private String endTime;//截止日期

    public XTask() {
    }

    public XTask(String[] array) {
        int i = 0;
        int len = array.length;
        this.project = len > i ? array[i++] : null;
        this.module = len > i ? array[i++] : null;
        this.name = len > i ? array[i++] : null;
        this.desc = len > i ? array[i++] : null;
        this.type = len > i ? array[i++] : null;
        this.relNeeds = len > i ? array[i++] : null;
        this.priority = len > i ? array[i++] : null;
        this.firstExpect = len > i ? array[i++] : null;
        this.expectStartTime = len > i ? array[i++] : null;
        this.endTime = len > i ? array[i++] : null;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRelNeeds() {
        return relNeeds;
    }

    public void setRelNeeds(String relNeeds) {
        this.relNeeds = relNeeds;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getFirstExpect() {
        return firstExpect;
    }

    public void setFirstExpect(String firstExpect) {
        this.firstExpect = firstExpect;
    }

    public String getExpectStartTime() {
        return expectStartTime;
    }

    public void setExpectStartTime(String expectStartTime) {
        this.expectStartTime = expectStartTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String[] toArray() {
        return new String[]{project, module, name, desc, type, relNeeds, priority, firstExpect, expectStartTime, endTime};
    }
}
