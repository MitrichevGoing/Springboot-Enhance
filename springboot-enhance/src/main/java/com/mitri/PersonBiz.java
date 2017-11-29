package com.mitri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title: Springboot-Enhance--com.mitri.PersonController</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:11 </p>
 * <p>Create DateTime: 2017/11/10 上午12:01 </p>
 *
 * @author Potter
 * @version v2
 */
@Service
public class PersonBiz {

  @Autowired
  private PersonService personService;

//  public PersonBiz(PersonService personService){
//    this.personService = personService;
//  }

  public void test(){
    System.out.println(personService.getName());
  }

}
