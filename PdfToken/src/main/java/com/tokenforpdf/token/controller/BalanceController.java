package com.tokenforpdf.token.controller;
import com.tokenforpdf.token.dto.BalanceDto;
import com.tokenforpdf.token.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/balance")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    @PostMapping("/buy")
    public ResponseEntity<BalanceDto> buyBalance(@RequestBody BalanceDto balanceDto) {
        BalanceDto newBalance = balanceService.buyTokens(balanceDto);
        return new ResponseEntity<>(newBalance, HttpStatus.CREATED);
    }
}
