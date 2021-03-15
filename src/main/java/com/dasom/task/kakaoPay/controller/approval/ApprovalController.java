package com.dasom.task.kakaoPay.controller.approval;

import com.dasom.task.kakaoPay.model.approval.Approval;
import com.dasom.task.kakaoPay.service.approve.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/approval")
public class ApprovalController {

    @Autowired
    private ApprovalService approvalService;


    @GetMapping("/getApproval")
    public Approval.ApprovalDocument getApproval(@RequestBody Approval.Search search) {
        return approvalService.getApproval(search);
    }


    @GetMapping("/getApprovalList")
    public List<Approval.ApprovalDocument> getApprovalList(@RequestBody Approval.Search search) {
        return approvalService.getApprovalList(search);
    }

}
