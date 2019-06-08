package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantDefinition;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.mapper.*;
import com.neuedu.lab.model.po.MedicalSkill;
import com.neuedu.lab.model.po.Prescription;
import com.neuedu.lab.model.po.PrescriptionContent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MedicineDepartService {
    @Resource
    MedicalSkillMapper medicalSkillMapper;
    @Resource
    RegisterMapper registerMapper;
    @Resource
    PatientMapper patientMapper;
    @Resource
    PrescriptionMapper prescriptionMapper;
    @Resource
    PrescriptionContentMapper prescriptionContentMapper;


    public JSONObject sendMedicine(Integer prescription_id){
        Prescription prescription = prescriptionMapper.getPrescription(prescription_id);
        if(prescription==null){
            return ConstantUtils.responseFail("无此处方记录");
        }else if(!prescription.getPrescription_execute_state().equals(ConstantDefinition.PRESCRIPTION_EXECUTE_STATE[3])){
            return ConstantUtils.responseFail("此处方状态为["+prescription.getPrescription_execute_state()+"],不可发药",prescription);
        }
        else {
            prescriptionMapper.updatePrescriptionState(prescription_id,ConstantDefinition.PRESCRIPTION_EXECUTE_STATE[4]);

            return ConstantUtils.responseSuccess(prescriptionMapper.getPrescription(prescription_id));
        }

    }


    @Transactional
    public JSONObject returnMedicine(Integer prescription_id, String prescription_medicine_id, Integer prescription_num){
        Prescription prescription = prescriptionMapper.getPrescription(prescription_id);

        PrescriptionContent prescriptionContent = new PrescriptionContent();
        prescriptionContent.setPrescription_id(prescription_id);
        prescriptionContent.setPrescription_medicine_id(prescription_medicine_id);
        prescriptionContent.setPrescription_num(prescription_num);

        //首先查看此条药品记录是否存在 根据处方ID和药物ID（不能根据药品记录ID）
        Integer prescriptionContentNum = prescriptionContentMapper.getPrescriptionContentNum(prescriptionContent);

        //查看药品状态应该是已领药状态
        if(!prescription.getPrescription_execute_state().equals(ConstantDefinition.PRESCRIPTION_EXECUTE_STATE[4])){
            return ConstantUtils.responseFail("该处方状态为["+prescription.getPrescription_execute_state()+"],不可退药",null);
        }

        //查看药品数量是否满足
        if(prescriptionContentNum == null || prescriptionContentNum < prescriptionContent.getPrescription_num()){
            return ConstantUtils.responseFail("药品数量大于可退费数量",null);
        }
        else {
            //获取最初的药品记录
            PrescriptionContent prescriptionContentToAdd = prescriptionContentMapper.getPrescriptionContentPositive(prescriptionContent);

            //改变数量、支付时间
            prescriptionContentToAdd.setPrescription_num(ConstantUtils.convertToNegtive(prescriptionContent.getPrescription_num()));

            prescriptionContentToAdd.setPrescription_create_time(null);
             prescriptionContentToAdd.setPrescription_consume_time(null);

            //增加退药的数量为负的药品记录
            prescriptionContentMapper.addPrescriptionContent(prescriptionContentToAdd);
            return ConstantUtils.responseSuccess(prescriptionContentToAdd);
        }
    }


}