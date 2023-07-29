package com.tokenforpdf.token.service;


import com.tokenforpdf.token.dto.BalanceDto;
import com.tokenforpdf.token.model.Balance;
import com.tokenforpdf.token.model.User;
import com.tokenforpdf.token.repository.BalanceRepository;
import com.tokenforpdf.token.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BalanceService {
    private final BalanceRepository balanceRepository;
    private final UserRepository userRepository;


    public BalanceDto buyTokens(BalanceDto balanceDto) {
        Balance balance = mapBalanceDtoToModel(balanceDto);
        Balance savedBalance = balanceRepository.save(balance);
        return mapBalanceModelToDto(savedBalance);
    }


    public List<BalanceDto> getAllBalances() {
        List<Balance> balances = balanceRepository.findAll();
        return balances.stream().map(this::mapBalanceModelToDto).collect(Collectors.toList());
    }

    private Balance mapBalanceDtoToModel(BalanceDto balanceDto) {
        Balance balance = new Balance();
        balance.setId(balanceDto.getId());
        balance.setTotal(balanceDto.getTotal());

        User user = userRepository.findById(balanceDto.getId()).orElse(null);
        balance.setUser(user);

        return balance;
    }

    private BalanceDto mapBalanceModelToDto(Balance balance) {
        BalanceDto balanceDto = new BalanceDto();
        balanceDto.setId(balance.getId());
        balanceDto.setTotal(balance.getTotal());

        if (balance.getUser() != null) {
            balanceDto.setId(balance.getUser().getId());
        }

        return balanceDto;
    }
}

