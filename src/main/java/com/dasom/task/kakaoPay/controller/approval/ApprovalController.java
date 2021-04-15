package com.dasom.task.kakaoPay.controller.approval;

import com.dasom.task.kakaoPay.exception.ApprovalBadRequestException;
import com.dasom.task.kakaoPay.exception.ApprovalException;
import com.dasom.task.kakaoPay.model.approval.Approval;
import com.dasom.task.kakaoPay.model.approval.ApprovalResponse;
import com.dasom.task.kakaoPay.model.enumclass.code.ApprovalCode;
import com.dasom.task.kakaoPay.service.approve.ApprovalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
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
    public ResponseEntity<ApprovalResponse<Approval>> getApproval(Approval.Search search) throws Exception{
        Approval approval = approvalService.getApproval(search);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApprovalResponse.of(ApprovalCode.FIND_SUCCESS.getMessageCode(),
                        ApprovalCode.FIND_SUCCESS.getCode(),
                        approval));
    }

    /**
     * 기안 리스트 조회
    * @param search
     * @return List<Approval.ApprovalDocument>
     */

    @GetMapping("/getApprovalList")
    public ResponseEntity<ApprovalResponse<List<Approval>>> getApprovalList(Approval.Search search) throws Exception {
        List<Approval> approvalDocumentList = approvalService.getApprovalList(search);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApprovalResponse.of(ApprovalCode.FIND_SUCCESS.getMessageCode(),
                        ApprovalCode.FIND_SUCCESS.getCode(),
                        approvalDocumentList));
    }

    /**
     * 기안 상신
     * @param param
     * @return ResponseEntity
     */

    @PostMapping
    public ResponseEntity registerApproval(@RequestBody @Valid Approval.Param param, Errors errors) throws Exception {
        try {
            Integer approvalId = approvalService.registerApproval(param);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApprovalResponse.of(ApprovalCode.REGISTER_SUCCESS.getMessageCode(),
                            ApprovalCode.REGISTER_SUCCESS.getCode(),
                            Boolean.TRUE, approvalId));

        } catch (Exception e) {
            log.error("register error");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApprovalResponse.of("register error", -1));
        }
    }

    /**
     * 기안 수정
     * @param param
     * @return ResponseEntity
     */

    @PutMapping("/update")
    public ResponseEntity updateApproval(@RequestBody @Valid Approval.Param param, Errors errors) throws Exception {
        try {
            Integer approvalId =approvalService.updateApproval(param);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApprovalResponse.of(ApprovalCode.REGISTER_SUCCESS.getMessageCode(),
                            ApprovalCode.REGISTER_SUCCESS.getCode(),
                            Boolean.TRUE, approvalId));

        } catch (Exception e) {
            log.error("update error");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApprovalResponse.of("update error", -1));
        }
    }

    /**
     * 기안 승인, 반려
     * @param param
     * @return ResponseEntity
     */

    @PutMapping("/process")
    public ResponseEntity processApproval(@RequestBody @Valid Approval.Param param, Errors errors) throws Exception {
        try {
            Integer approvalId = approvalService.processApproval(param);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApprovalResponse.of(ApprovalCode.REGISTER_SUCCESS.getMessageCode(),
                            ApprovalCode.REGISTER_SUCCESS.getCode(),
                            Boolean.TRUE, approvalId));

        } catch (Exception e) {
            log.error("process error");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApprovalResponse.of("process error", -1));
        }
    }

    /**
     * 기안 삭제
     * @param param
     * @return ResponseEntity
     */

    @DeleteMapping
    public ResponseEntity deleteApproval(@RequestBody @Valid Approval.Param param, Errors errors) throws Exception {

        try {
            approvalService.deleteApproval(param);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApprovalResponse.of(ApprovalCode.REGISTER_SUCCESS.getMessageCode(),
                            ApprovalCode.REGISTER_SUCCESS.getCode(),
                            Boolean.TRUE));

        } catch (Exception e) {
            log.error("delete error");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApprovalResponse.of("delete error", -1));
        }
    }

    /**
     * ApprovalException 핸들러
     * @param e
     * @return ResponseEntity
     */
    @ExceptionHandler(ApprovalException.class)
    public ResponseEntity<ApprovalResponse<Boolean>> approvalServerException(ApprovalException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApprovalResponse.of(e.getMessage(), e.getCode(), Boolean.FALSE));
    }

    /**
     * ApprovalBadRequestException 핸들러
     * @param e
     * @return ResponseEntity
     */

    @ExceptionHandler(ApprovalBadRequestException.class)
    public ResponseEntity<ApprovalResponse<Boolean>> approvalBadRequestException(ApprovalBadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApprovalResponse.of(e.getMessage(), e.getCode(), Boolean.FALSE));
    }
}
