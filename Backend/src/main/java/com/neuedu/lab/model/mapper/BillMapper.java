package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillMapper {
    public void addBill(Bill bill);

    public Bill getRegisterBillForRefund(@Param("register_id") Integer register_id,
                                         @Param("bill_state0") String bill_state0,
                                         @Param("bill_state2") String bill_state2,
                                         @Param("bill_state3") String bill_state3);

    Bill getBillById(Integer bill_id);

    Integer getLastBillId();
    List<Bill> getBillForOneRecord(Integer register_id);

    List<Bill> getBillByUserIdAndTime(@Param("bill_user_id") Integer bill_user_id,
                                      @Param("start_time") String start_time,
                                      @Param("end_time") String end_time);

    //供退费使用
    List<Bill> getBillByMedicalSkillId(Integer bill_medical_skill_id);

    Bill getBillByPrescriptionId(Integer bill_prescription_id);

    void updateBillNum(@Param("bill_num") String bill_num,@Param("bill_id")Integer bill_id);

    //日结冻结发票
    void updateBillFrozen(@Param("bill_user_id") Integer bill_user_id,
                          @Param("start_time") String start_time,
                          @Param("end_time") String end_time);

    void updateBillState(@Param("bill_state") String bill_state,@Param("bill_id") Integer bill_id);
}
