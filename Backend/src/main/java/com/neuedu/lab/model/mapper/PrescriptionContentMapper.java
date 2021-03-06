package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Prescription;
import com.neuedu.lab.model.po.PrescriptionContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrescriptionContentMapper {
    void addPrescriptionContent(PrescriptionContent prescriptionContent);//加药
    void deletePrescriptionContents(Integer prescription_id);//删除处方
    void deletePrescriptionContent(Integer prescription_content_id);//删药

    //供退药使用，仅返回未被消费的药品
    List<PrescriptionContent> getPrescriptionContentsNew(Integer prescription_id);

    List<PrescriptionContent> getPrescriptionContentsPart(Integer prescription_id);

    //供病历填充内容使用
    List<PrescriptionContent> getPrescriptionContents(Integer prescription_id);

    PrescriptionContent getPrescriptionContent(Integer prescription_id, String prescription_medicine_id);

    //供退药使用,返回一个处方下一种药品的所有记录（数量）
    Integer getPrescriptionContentNum (PrescriptionContent prescriptionContent);


    //供退药使用，返回数量为正的所有药品记录
    List<PrescriptionContent> getPrescriptionContentsPositive(Integer prescription_id);


    //供删除药品使用
    PrescriptionContent getPrescriptionContentById(Integer prescription_content_id);


    //供退药使用， 更新内容
    void updatePrescriptionContent(PrescriptionContent prescriptionContent);

    //供退药使用，更新处方ID
    void updatePrescriptionId(@Param("prescription_id_before") Integer prescription_id_before,
                              @Param("prescription_id") Integer prescription_id);
}
