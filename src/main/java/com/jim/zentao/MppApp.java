package com.jim.zentao;

import net.sf.mpxj.ProjectFile;
import net.sf.mpxj.Task;
import net.sf.mpxj.TaskContainer;
import net.sf.mpxj.mpp.MPPReader;

import java.io.File;
import java.io.FileInputStream;

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
        String pathname = "/Users/qiao/Downloads/2007.mpp";
        File file = new File(pathname);
        FileInputStream in = new FileInputStream(file);

        MPPReader reader = new MPPReader();
        ProjectFile projectFile = reader.read(in);
        TaskContainer tasks = projectFile.getAllTasks();

        for (Task task : tasks) {
            System.out.println(task.getName());
        }
    }
}
