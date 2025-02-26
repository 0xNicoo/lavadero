package com.nicolas.Lavadero.controller;

import com.nicolas.Lavadero.dto.request.PaymentDTOIn;
import com.nicolas.Lavadero.dto.request.ServiceTypeDTOIn;
import com.nicolas.Lavadero.dto.response.PaymentDTO;
import com.nicolas.Lavadero.dto.response.ServiceTypeDTO;
import com.nicolas.Lavadero.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/payment")
@Tag(name = "Payment", description = "Payment endpoints")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Operation(summary = "Create payment")
    @PostMapping
    public ResponseEntity<PaymentDTO> create(@RequestBody PaymentDTOIn paymentDTOIn){
        PaymentDTO response = paymentService.create(paymentDTOIn);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all payments")
    @GetMapping("/all")
    public ResponseEntity<List<PaymentDTO>> getAll(){
        List<PaymentDTO> response = paymentService.getAll();
        return ResponseEntity.ok(response);
    }

}
