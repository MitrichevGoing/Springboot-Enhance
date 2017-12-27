package com.mitri.po;

import com.xuxueli.poi.excel.annotation.ExcelField;
import com.xuxueli.poi.excel.annotation.ExcelSheet;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;

/**
 * <p>Title: Springboot-Enhance--com.mitri.po.PersonModel</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:11 </p>
 * <p>Create DateTime: 2017/11/21 下午8:05 </p>
 *
 * @author Potter
 * @version v2
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ExcelSheet(name = "用户列表", headColor = HSSFColorPredefined.DARK_YELLOW)
public class PersonModel {

  @ExcelField(name = "用户姓名")
  private String name;
  @ExcelField(name = "用户年龄")
  @Max(value = 20, message = "年龄必须在20，30之间")
//  @Size(min = 20,max = 30,message = "年龄必须在20，30之间")
  private Integer age;
  @ExcelField(name = "用户地址")
  @NotNull(message = "地址不能为空")
  private String address;

}
