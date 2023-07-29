package com.tokenforpdf.token.repository;
import com.tokenforpdf.token.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}