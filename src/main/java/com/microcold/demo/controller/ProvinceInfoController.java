package com.microcold.demo.controller;

import com.microcold.demo.entity.ProvinceInfo;
import com.microcold.demo.service.ProvinceInfoService;
import com.microcold.demo.until.read;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.microcold.demo.until.read;

import java.io.IOException;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/all/")
public class ProvinceInfoController
{
  @Autowired
    ProvinceInfoService provinceInfoService;
  @GetMapping(value="first")//第一次手动写入所有数据
  public String addall()throws IOException
  {
      String path="C:\\Users\\micro\\Desktop\\china region\\省界_region.dbf";
      String charset="GB2312";
      String[][] title = read.getFieldName(path, charset);
      for(int i=0;i<title.length;i++){
          provinceInfoService.add(title[i][0],title[i][1]);
      }
      return "test";
  }
  @GetMapping(value="list")
  public List<ProvinceInfo> getProvinceInfo(){
      return provinceInfoService.findProvinceInfoList();
  }
  @GetMapping(value="/{id}")
  public ProvinceInfo getProvinceInfoById(@PathVariable("id") int id){
      return provinceInfoService.findProvinceInfo(id);
  }
  @PutMapping(value="/{id}")
  public String  updateProvinceInfo(@PathVariable("id") int id,
                                   @RequestParam(value="infoName",required = true) String infoName,
                                   @RequestParam(value="typeName",required = true) String typeName){
      int t= provinceInfoService.update(id,infoName,typeName);
      if(t==1){
          return "success";
      }else{
          return "failed";
      }
  }
  @DeleteMapping(value="/{id}")
  public String delete(@PathVariable(value = "id") int id){
      int t=provinceInfoService.delete(id);
      if(t==1){
          return "success";
      }else{
          return "failed";
      }
  }

  @PostMapping(value="")
    public String postProvinceInfo(@RequestParam(value="infoName") String infoName,
                                   @RequestParam(value="typeName") String typeName){
      int t=provinceInfoService.add(infoName,typeName);
      if(t==1){
          return "success";
      }else{
          return "failed";
      }
  }



}
