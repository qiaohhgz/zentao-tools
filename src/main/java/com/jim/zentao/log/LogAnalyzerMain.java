package com.jim.zentao.log;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author jim乔仕举
 * @version V1.0
 * @Title: LogAnalyzerMain.java
 * @Package com.jim.zentao.log
 * @Description:
 * @date 2016年03月21日 下午12:48
 */
public class LogAnalyzerMain {
    public static void main(String[] args) throws IOException, InterruptedException {

        String[] fileNames = {
                "/Users/qiao/Downloads/Desktop01/catalina.2016-03-18-23-58-01.out",
                "/Users/qiao/Downloads/Desktop01/catalina.2016-03-19-23-58-01.out",
                "/Users/qiao/Downloads/Desktop01/catalina.2016-03-20-23-58-01.out",
                "/Users/qiao/Downloads/Desktop02/catalina.2016-03-18-23-58-01.out",
                "/Users/qiao/Downloads/Desktop02/catalina.2016-03-19-23-58-01.out",
                "/Users/qiao/Downloads/Desktop02/catalina.2016-03-20-23-58-01.out",
        };
        File excelFile = new File("/Users/qiao/Downloads/Desktop01/count.xlsx");
        File mdFile = new File("/Users/qiao/Downloads/Desktop01/count.md");
        int sleepTime = 1;
        // 删除导出文件
        if (excelFile.exists()) {
            excelFile.delete();
        }
        if(mdFile.exists()){
            mdFile.delete();
        }

        Map<String, FileLine> errors = new HashMap<String, FileLine>();
        int lineNumber = 0;
        //
        for (int i = 0; i < fileNames.length; i++) {
            File logFile = new File(fileNames[i]);


            System.out.printf("开始读取log文件 > %s,日志长度：%d%n", logFile.getName(), logFile.length());

            LineIterator lineIterator = FileUtils.lineIterator(logFile, "GB2312");
            lineNumber = 0;//行数
            List<String> ctxLines = new ArrayList<String>();// 用来截取完整日志
            FileLine fileLine = null;
            // 遍历日志
            while (lineIterator.hasNext()) {
                lineNumber++;

                String line = lineIterator.next();

                if (line.contains("[ERROR]")) {// 是一个错误日志
                    System.out.printf("在第%d行读取到一条错误日志, 错误日志总类%d%n", lineNumber, errors.size());
                    ctxLines.add(line);//第一行错误日志
                    fileLine = new FileLine(lineNumber).setLogFileName(logFile.getName());
                } else if (line.contains("[INFO]") || line.contains("[DEBUG]") || line.contains("[WARN]")) {

                    if (ctxLines.isEmpty()) {
                        continue;
                    } else {// 错误信息结尾
                        // 截取错误信息
                        String firstError = ctxLines.get(0);
                        String msg = firstError.substring(firstError.lastIndexOf("]") + 1);
                        if (errors.containsKey(msg)) {//增加出现次数
                            errors.get(msg).addLine();
                        } else {//第一次出现该异常
                            fileLine.setLines(ctxLines);
                            errors.put(msg, fileLine);
                            System.out.printf("新添加一种异常信息,整个错误上线文行数为%d\n", ctxLines.size());
                        }
                        ctxLines.clear();//清空存储的错误信息
                    }

                } else {
                    if (!ctxLines.isEmpty()) {// 中间的日志
                        // 保存完整的错误信息
                        ctxLines.add(line);
                    }
                }


//                Thread.sleep(sleepTime);
            }

            // 换文件之前完结上下文
            if (!ctxLines.isEmpty()) {
                // 截取错误信息
                String firstError = ctxLines.get(0);
                String msg = firstError.substring(firstError.lastIndexOf("]") + 1);
                if (errors.containsKey(msg)) {//增加出现次数
                    errors.get(msg).addLine();
                } else {//第一次出现该异常
                    fileLine.setLines(ctxLines);
                    errors.put(msg, fileLine);
                    System.out.printf("新添加一种异常信息,整个错误上线文行数为%d\n", ctxLines.size());
                }
                ctxLines.clear();//清空存储的错误信息
            }

        }

        // 导出日志
        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        int rowIndex = 0;
        int cellIndex = 0;

        Row row = sheet.createRow(rowIndex++);
        row.createCell(cellIndex++).setCellValue("所在文件");
        row.createCell(cellIndex++).setCellValue("所在行数");
        row.createCell(cellIndex++).setCellValue("错误概括");
        row.createCell(cellIndex++).setCellValue("错误详情");
        row.createCell(cellIndex++).setCellValue("出现次数");

        Set<String> keys = errors.keySet();
        for (String key : keys) {
            FileLine fileLine = errors.get(key);

            row = sheet.createRow(rowIndex++);
            cellIndex = 0;

            row.createCell(cellIndex++).setCellValue(fileLine.getLogFileName());
            row.createCell(cellIndex++).setCellValue(fileLine.getFirstLineNumber());
            row.createCell(cellIndex++).setCellValue(key);
            row.createCell(cellIndex++).setCellValue(fileLine.lineToString());
            row.createCell(cellIndex++).setCellValue(fileLine.getCount());
        }

        System.out.println("开始导出excel...");
        workbook.write(new FileOutputStream(excelFile));
        System.out.println("导出成功");

        // 导出MarkDown
        List<String> list = new ArrayList<String>();
        for (String key : keys) {
            FileLine fileLine = errors.get(key);
            list.add("### [" + fileLine.getFirstLineNumber() + "]" + key);
            list.add("```java");
            List<String> lines = fileLine.getLines();
            for (String line : lines) {
                list.add(line);
            }
            list.add("```");
            list.add("");
        }

        FileUtils.writeLines(mdFile, list);
    }
}
