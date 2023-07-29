package com.tokenforpdf.token.repository;
import com.tokenforpdf.token.model.Balance;
import com.tokenforpdf.token.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
}