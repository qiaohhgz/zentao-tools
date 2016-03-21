package com.jim.zentao.log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jim乔仕举
 * @version V1.0
 * @Title: FileLine.java
 * @Package com.jim.zentao.log
 * @Description:
 * @date 2016年03月21日 下午12:55
 */
public class FileLine {
    private List<String> lines = new ArrayList<String>();
    private int count = 1;
    private int firstLineNumber = -1;
    private String logFileName;

    public FileLine() {
    }

    public FileLine(int firstLineNumber) {
        this.firstLineNumber = firstLineNumber;
    }

    @Deprecated
    public FileLine(List<String> lines) {
        this.lines = new ArrayList<String>(lines);
    }

    public FileLine(LimitQueue<String> limitQueue, List<String> lines) {
        for (int i = 0; i < limitQueue.size() - 1; i++) {
            getLines().add(limitQueue.poll());
        }
        getLines().addAll(lines);
    }

    public FileLine addLine() {
        this.count++;
        return this;
    }

    public List<String> getLines() {
        return lines;
    }

    public String lineToString() {
        StringBuilder builder = new StringBuilder();
        for (String line : lines) {
            builder.append(line).append("\n");
        }
        return builder.toString();
    }

    public int getCount() {
        return count;
    }

    public FileLine setLines(List<String> lines) {
        this.lines = new ArrayList<String>(lines);
        return this;
    }

    public FileLine setFirstLineNumber(int firstLineNumber) {
        this.firstLineNumber = firstLineNumber;
        return this;
    }

    public int getFirstLineNumber() {
        return firstLineNumber;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public FileLine setLogFileName(String logFileName) {
        this.logFileName = logFileName;
        return this;
    }
}
