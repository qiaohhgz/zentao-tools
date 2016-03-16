package com.jim.zentao.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jim乔仕举
 * @version V1.0
 * @Title: XTask.java
 * @Package com.jim.zentao.model
 * @Description: excl task
 * @date 2016年03月15日 下午5:55
 */
public class XTaskPage {
    private String name;
    private List<XTask> tasks = new ArrayList<XTask>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<XTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<XTask> tasks) {
        this.tasks = tasks;
    }
}
