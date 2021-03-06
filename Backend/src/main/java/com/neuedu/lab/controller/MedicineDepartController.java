package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;

import com.neuedu.lab.model.service.MedicineDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/md-doctor")
public class MedicineDepartController {
    @Autowired
    MedicineDepartService medicineDepartService;

    @RequestMapping("/get-prescription-by-patient")
    public JSONObject getPrescriptionByPatientId(@RequestBody JSONObject request){
        return medicineDepartService.getPrescriptionByPatientIdByPayTime(
                request.getInteger("register_info_patient_id"),
                request.getDate("start_time"),
                request.getDate("end_time"));
    }

    @RequestMapping("/get-all-patients")
    public JSONObject getAllPatients(){
        return medicineDepartService.getAllPatients();
    }

    //发药
    @RequestMapping("/send-medicine")
    public JSONObject sendMedicine(@RequestBody JSONObject request){
        return medicineDepartService.sendMedicine(request.getInteger("prescription_id"),
                request.getInteger("prescription_execute_doctor_id"));
    }

    @RequestMapping("/return-medicine")
    public JSONObject returnMedicine(@RequestBody JSONObject request){
        return medicineDepartService.returnMedicine(request.getInteger("prescription_id"),
                request.getInteger("prescription_content_id"),request.getInteger("prescription_num"));
    }

    @RequestMapping("/get-sent-prescription")
    public JSONObject getSentPrescription(@RequestBody JSONObject request){
        return  medicineDepartService.getSentPrescription(request.getInteger("patient_id"),
                request.getDate("start_time"), request.getDate("end_time"));
    }

    @RequestMapping("/get-prescription-contents")
    public JSONObject getPrescriptionContentsByPrescriptionId(@RequestBody JSONObject request){
        return medicineDepartService.getPrescriptionContentsByPrescriptionId(request.getInteger("prescription_id"));
    }
}
