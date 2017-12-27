package com.mitri.controller;

import com.mitri.po.PersonModel;
import com.mitri.po.StockModel;
import com.mitri.service.PushService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.xuxueli.poi.excel.ExcelExportUtil;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * <p>Title: Springboot-Enhance--com.mitri.controller.TestDeployController</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:11 </p>
 * <p>Create DateTime: 2017/11/13 下午10:33 </p>
 *
 * @author Potter
 * @version v2
 */
@RestController
public class TestDeployController {

//  @Autowired
//  private CuratorFramework zkClient;

  @GetMapping("/test")
  public String test(){
    return "test abhjbjhs大脚aagfgaaa";
  }

//  @GetMapping("/zk/{id}")
//  public String zkTest(@PathVariable String id){
//    String path = "/lock/zk/" + id;
//    System.out.println("try to do job for "+ id);
//    try{
//      InterProcessMutex lock = new InterProcessMutex(zkClient,path);
//      if(lock.acquire(10, TimeUnit.SECONDS)){
//        try {
//          Thread.sleep(1000*5);
//          System.out.println("do job "+ id + "done");
//        }finally {
//          lock.release();
//        }
//      }
//    }catch (Exception e){
//      System.out.println(e.getMessage());
//    }
//    return id;
//  }


  @Autowired
  PushService pushService; //①

  @RequestMapping("/defer")
  @ResponseBody
  public DeferredResult<String> deferredCall() { //②
    return pushService.getAsyncUpdate();
  }

  @GetMapping("/ng/list")
  public List<PersonModel> testNgList(){
    List<PersonModel> list = new ArrayList<>();
    list.add(new PersonModel("曹操",20,"苏州"));
    list.add(new PersonModel("孙权",23,"无锡"));
    list.add(new PersonModel("刘备",29,"常州"));
    list.add(new PersonModel("诸葛亮",22,"南京"));
    return list;
  }

  @GetMapping("/ng/stock")
  public List<StockModel> testStock(){
    List<StockModel> list = new ArrayList<>();
    list.add(new StockModel("1","第1只股票",1.99,3.5));
    list.add(new StockModel("2","第2只股票",2.99,4.5));
    list.add(new StockModel("3","第3只股票",3.99,2.5));
    list.add(new StockModel("4","第4只股票",5.99,1.5));
    list.add(new StockModel("5","第5只股票",2.99,1.0));
    list.add(new StockModel("6","第6只股票",1.39,4.0));
    list.add(new StockModel("8","第7只股票",2.79,3.5));
    list.add(new StockModel("9","第8只股票",4.99,5.0));
    list.add(new StockModel("10","第9只股票",9.99,5.0));
    list.add(new StockModel("11","第10只股票",7.99,2.5));
    list.add(new StockModel("12","第11只股票",5.99,3.5));
    return list;
  }

  @GetMapping("/excel")
  public String excelTest(HttpServletResponse response){
    List<PersonModel> list = new ArrayList<>();
    list.add(new PersonModel("曹操",20,"苏州"));
    list.add(new PersonModel("孙权",23,"无锡"));
    list.add(new PersonModel("刘备",29,"常州"));
    list.add(new PersonModel("诸葛亮",22,"南京"));

    Workbook workbook = ExcelExportUtil.exportWorkbook(list);

    response.setHeader("Content-Disposition", "attachment;filename=" + LocalDateTime.now() + ".xls");
    response.setContentType("application/vnd.ms-excel;charset=UTF-8");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);

    try {

      OutputStream output = response.getOutputStream();
      BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
      bufferedOutPut.flush();
      workbook.write(bufferedOutPut);
      bufferedOutPut.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

    return "下载成功";
  }

  @PostMapping("/valid")
  public String testValid(@RequestBody @Validated PersonModel model,
      BindingResult result){
    if(result.hasErrors()){
      return result.getAllErrors().stream().map(it-> it.getDefaultMessage()).collect(Collectors.joining(","));
    }
    return "success";
  }

}
