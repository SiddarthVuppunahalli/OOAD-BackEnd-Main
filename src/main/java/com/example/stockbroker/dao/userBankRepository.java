package com.example.stockbroker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userBankRepository extends JpaRepository<bankdetails,Long> {
    List<bankdetails> findBankDetailsByEmail(String email);

    List<bankdetails> findBankDetailsByAccountno(Long accountno);
}
