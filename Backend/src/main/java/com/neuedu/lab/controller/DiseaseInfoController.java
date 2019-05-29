package com.neuedu.lab.controller;

import com.neuedu.lab.model.po.DiseaseInfo;
import com.neuedu.lab.model.service.DiseaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/diseaseinfo")
public class DiseaseInfoController {
    @Autowired
    private DiseaseInfoService diseaseInfoService;

    @RequestMapping("/getall")
//    显示所有疾病信息
    public List<DiseaseInfo> getAllDiseases(){
        return diseaseInfoService.getAllDiseases();
    }

    @RequestMapping("/get")
//    显示查询疾病信息
    public List<DiseaseInfo> getDisease(@RequestParam String disease_id){
        return diseaseInfoService.getDisease(disease_id);
    }

    @RequestMapping("/add")
    public String addDisease(@RequestParam DiseaseInfo diseaseInfo){
        if(diseaseInfoService.addDisease(diseaseInfo)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    @RequestMapping("/update")
    public String updateConstant(@RequestParam DiseaseInfo diseaseInfo){
        if(diseaseInfoService.updateDisease(diseaseInfo)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    @RequestMapping("/delete")
    public String deleteConstant(@RequestParam DiseaseInfo diseaseInfo){
        if(diseaseInfoService.deleteDisease(diseaseInfo)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }
}
