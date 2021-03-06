package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.model.mapper.BillMapper;
import com.neuedu.lab.model.po.Bill;
import com.neuedu.lab.utils.ConstantDefinition;
import com.neuedu.lab.utils.ConstantUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BillService {
    @Resource
    private BillMapper billMapper;

//补打
    public JSONObject rePrintBill(Integer bill_id){
        billMapper.updateBillState(ConstantDefinition.BILL_STATE[3],bill_id);
        Bill newBill = billMapper.getBillById(bill_id);
        return ConstantUtils.responseSuccess("补打成功",newBill);
    }
//重打
    public JSONObject overPrintBill(Integer bill_id){
        Bill previousBill = billMapper.getBillById(bill_id);
        billMapper.updateBillState(ConstantDefinition.BILL_STATE[1],bill_id);
        billMapper.addBill(previousBill);
        Integer lastBillId = billMapper.getLastBillId();
        billMapper.updateBillState(ConstantDefinition.BILL_STATE[2],lastBillId);
        Bill newBill = billMapper.getBillById(lastBillId);
        return ConstantUtils.responseSuccess("重打成功",newBill);
    }
}
