package com.dasom.task.kakaoPay.controller.approval;

import com.dasom.task.kakaoPay.model.approval.Approval;
import com.dasom.task.kakaoPay.model.approval.ApprovalResponse;
import com.dasom.task.kakaoPay.model.enumclass.code.ApprovalCode;
import com.dasom.task.kakaoPay.service.approve.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/approval")
public class ApprovalController {


    @Autowired
    private ApprovalService approvalService;

    /**
     * 기안 조회
     * @param search
     * @return Approval.ApprovalDocument
     */

    @GetMapping("/getApproval")
    public ResponseEntity<ApprovalResponse<Approval.ApprovalDocument>> getApproval(Approval.Search search) {
        Approval.ApprovalDocument approvalDocument = approvalService.getApproval(search);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApprovalResponse.of(ApprovalCode.FIND_SUCCESS.getMessageCode(),
                        ApprovalCode.FIND_SUCCESS.getCode(),
                        approvalDocument));
    }

    /**
     * 기안 리스트 조회
    * @param search
     * @return List<Approval.ApprovalDocument>
     */

    @GetMapping("/getApprovalList")
    public ResponseEntity<ApprovalResponse<List<Approval.ApprovalDocument>>> getApprovalList(Approval.Search search) {
        List<Approval.ApprovalDocument> approvalDocumentList = approvalService.getApprovalList(search);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApprovalResponse.of(ApprovalCode.FIND_SUCCESS.getMessageCode(),
                        ApprovalCode.FIND_SUCCESS.getCode(),
                        approvalDocumentList));
    }

    /**
     * 기안 상신
     * @param approval
     * @return ResponseEntity
     */

    @PostMapping("/registerApproval")
    @ResponseBody
    public ResponseEntity registerApproval(@RequestBody Approval.AddParam approval) {
        System.out.println(approval);
      //  approvalService.registerApproval(approval);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApprovalResponse.of(ApprovalCode.REGISTER_SUCCESS.getMessageCode(),
                        ApprovalCode.REGISTER_SUCCESS.getCode(),
                        Boolean.TRUE));
    }

    /**
     * 기안 수정
     * @param approval
     * @return ResponseEntity
     */

    @PutMapping("/updateApproval")
    public ResponseEntity updateApproval(Approval approval) {
        approvalService.updateApproval(approval);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApprovalResponse.of(ApprovalCode.UPDATE_SUCCESS.getMessageCode(),
                        ApprovalCode.UPDATE_SUCCESS.getCode(),
                        Boolean.TRUE));
    }

    /**
     * 기안 삭제
     * @param approval
     * @return ResponseEntity
     */

    @DeleteMapping("/deteleApproval")
    public ResponseEntity deleteApproval(Approval approval) {
        approvalService.deleteApproval(approval);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApprovalResponse.of(ApprovalCode.DELETE_SUCCESS.getMessageCode(),
                        ApprovalCode.DELETE_SUCCESS.getCode(),
                        Boolean.TRUE));
    }

}
