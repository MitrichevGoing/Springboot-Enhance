package com.mitri.po;

import javax.persistence.criteria.CriteriaBuilder.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

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
public class PersonModel {

  private String name;

  private Integer age;

  private String address;

}
