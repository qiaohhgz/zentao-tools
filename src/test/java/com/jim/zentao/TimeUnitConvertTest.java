package com.jim.zentao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author jim乔仕举
 * @version V1.0
 * @Title: TimeUnitConvertTest.java
 * @Package com.jim.zentao
 * @Description:
 * @date 2016年03月17日 下午1:27
 */
public class TimeUnitConvertTest {
    public static void main(String[] args) {
        Map<net.sf.mpxj.TimeUnit, MppApp.TimeUnitConvert> map = new HashMap<net.sf.mpxj.TimeUnit, MppApp.TimeUnitConvert>();
        map.put(net.sf.mpxj.TimeUnit.MINUTES, new MppApp.TimeUnitConvert(1, TimeUnit.MINUTES));
        map.put(net.sf.mpxj.TimeUnit.HOURS, new MppApp.TimeUnitConvert(1, TimeUnit.HOURS));
        map.put(net.sf.mpxj.TimeUnit.DAYS, new MppApp.TimeUnitConvert(1, TimeUnit.DAYS));
        map.put(net.sf.mpxj.TimeUnit.WEEKS, new MppApp.TimeUnitConvert(7, TimeUnit.DAYS));
        map.put(net.sf.mpxj.TimeUnit.MONTHS, new MppApp.TimeUnitConvert(30, TimeUnit.DAYS));
        map.put(net.sf.mpxj.TimeUnit.YEARS, new MppApp.TimeUnitConvert(365, TimeUnit.DAYS));
        long hours = map.get(net.sf.mpxj.TimeUnit.WEEKS).convert(1, TimeUnit.HOURS);
        System.out.println(hours);
    }

}