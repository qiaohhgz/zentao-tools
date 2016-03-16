package com.jim.zentao;

import com.jim.zentao.model.XTask;
import com.jim.zentao.model.XTaskPage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Hello world!
 */
public class App {


    public static void main(String[] args) throws IOException, InvalidFormatException {
        System.out.println("Hello World!");

        File file = new File("/Users/qiao/Downloads/input1.xlsx");
        file.delete();

        String[] headNames = {"所属项目", "所属模块", "任务名称", "任务描述", "任务类型", "相关需求", "优先级", "最初预计", "预计开始", "截止日期"};

        XTaskPage page = new XTaskPage();
        page.setName("任务");
        page.getTasks().add(new XTask(headNames));
        page.getTasks().add(new XTask(new String[]{"项目(#244)", "/项目/模块(#34)", "统计(#646)", "切页面", "", "设计", "3", "2016-01-28 00:00:00", "0000-00-00 00:00:00", "2016-01-28 00:00:00", "未开始", "8", "", "8"}));
        page.getTasks().add(new XTask(new String[]{"项目(#244)", "/项目/模块(#34)", "统计(#646)", "切页面", "", "设计", "3", "2016-01-28 00:00:00", "0000-00-00 00:00:00", "2016-01-28 00:00:00", "未开始", "8", "", "8"}));

        pageToExcel(file, page);
    }

    private static void pageToExcel(File file, XTaskPage page) throws IOException {
        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet(page.getName());
        int rowIndex = 0;
        // data
        for (int i = 0; i < page.getTasks().size(); i++) {
            Row row = sheet.createRow(rowIndex++);
            String[] cellData = page.getTasks().get(i).toArray();
            for (int j = 0; j < cellData.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(cellData[j]);
            }
        }

        workbook.write(new FileOutputStream(file));
        System.out.println("OK");
    }
}
