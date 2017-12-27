package com.mitri.excel;

import com.mitri.po.PersonModel;
import com.xuxueli.poi.excel.ExcelExportUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: Springboot-Enhance--com.mitri.excel.ExcelTest</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:12 </p>
 * <p>Create DateTime: 2017/12/22 下午1:56 </p>
 *
 * @author Potter
 * @version v2
 */
public class ExcelTest {

  public static void main(String[] args) {
    List<PersonModel> personModels = new ArrayList<>();
    personModels.add(new PersonModel("张飞",20,"苏州"));
    personModels.add(new PersonModel("李白",24,"苏州"));
    personModels.add(new PersonModel("关羽",26,"苏州"));

    ExcelExportUtil.exportToFile("/Users/potter/Desktop/test.xls", personModels);
  }

}
