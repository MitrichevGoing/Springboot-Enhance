package com.mitri.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>Title: Springboot-Enhance--com.mitri.po.StockModel</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:11 </p>
 * <p>Create DateTime: 2017/11/21 下午11:05 </p>
 *
 * @author Potter
 * @version v2
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class StockModel {

  private String name;

  private Double price;

  private Double rating;

}
