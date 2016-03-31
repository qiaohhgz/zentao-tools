package com.jim.zentao;

import com.jim.zentao.model.XTask;
import com.jim.zentao.model.XTaskPage;
import net.sf.mpxj.*;
import net.sf.mpxj.TimeUnit;
import net.sf.mpxj.mpp.MPPReader;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author jim乔仕举
 * @version V1.0
 * @Title: MppApp.java
 * @Package com.jim.zentao
 * @Description:
 * @date 2016年03月16日 上午10:03
 */
public class MppApp {
    public static void main(String[] args) throws Exception {
        // 参数
        String pathname = "/Users/qiao/Downloads/项目1.mpp";
        File file = new File(pathname);
        FileInputStream in = new FileInputStream(file);

        //
        MPPReader reader = new MPPReader();
        ProjectFile projectFile = reader.read(in);
        TaskContainer tasks = projectFile.getAllTasks();

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/MM HH:mm:ss");
        XTaskPage page = new XTaskPage();
        String[] headNames = {"所属项目", "所属模块", "任务名称", "任务描述", "任务类型", "相关需求", "优先级", "最初预计", "预计开始", "截止日期"};
        page.setName("任务");
        page.getTasks().add(new XTask(headNames));

        String project = "";
        String module = "";
        for (Task task : tasks) {
            String taskType = "开发";
            String needs = "";

            String resource = "";
            if (task.getResourceAssignments() != null && !task.getResourceAssignments().isEmpty()) {
                List<ResourceAssignment> resourceAssignments = task.getResourceAssignments();
                for (ResourceAssignment resourceAssignment : resourceAssignments) {
                    resource += resourceAssignment.getResource().getName() + ",";
                }
            }
            String taskName = task.getName();
            TimeUnit units = task.getDuration().getUnits();

            java.util.concurrent.TimeUnit toType = java.util.concurrent.TimeUnit.HOURS;
            Map<TimeUnit, java.util.concurrent.TimeUnit> map = new HashMap<TimeUnit, java.util.concurrent.TimeUnit>();
            map.put(TimeUnit.MINUTES, java.util.concurrent.TimeUnit.MINUTES);
            map.put(TimeUnit.HOURS, java.util.concurrent.TimeUnit.HOURS);
            map.put(TimeUnit.DAYS, java.util.concurrent.TimeUnit.DAYS);
            map.put(TimeUnit.WEEKS, java.util.concurrent.TimeUnit.DAYS);
            map.put(TimeUnit.MONTHS, java.util.concurrent.TimeUnit.DAYS);
            map.put(TimeUnit.YEARS, java.util.concurrent.TimeUnit.DAYS);

            java.util.concurrent.TimeUnit unit = map.get(units);
            long convert = unit.convert((long) task.getDuration().getDuration(), toType);
            String start = format.format(task.getStart());
            String finish = format.format(task.getFinish());


            System.out.printf("%s\t%s\t%s\t%s\t%s", taskName, null, start, finish, resource);
            System.out.println();

            page.getTasks().add(new XTask(new String[]{project, module, taskName, taskName, taskType, needs, "", "".toString(),}));

        }
    }

    public static class TimeUnitConvert{
        private int diffValue;
        private java.util.concurrent.TimeUnit timeUnit;

        public TimeUnitConvert(int diffValue, java.util.concurrent.TimeUnit timeUnit) {
            this.diffValue = diffValue;
            this.timeUnit = timeUnit;
        }

        public long convert(int value, java.util.concurrent.TimeUnit sourceUnit) {
            return sourceUnit.convert(value * diffValue, timeUnit);
        }
    }


}
