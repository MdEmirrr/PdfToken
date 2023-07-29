package com.tokenforpdf.token.service;

import com.tokenforpdf.token.dto.BalanceDto;
import com.tokenforpdf.token.dto.DocumentDto;
import com.tokenforpdf.token.dto.UserDto;
import com.tokenforpdf.token.model.Document;
import com.tokenforpdf.token.model.User;
import com.tokenforpdf.token.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final BalanceService balanceService;


    public DocumentDto uploadDocument(DocumentDto documentDto) {
        Document document = new Document();
        // Document document = modelMapper.map(documentDto, Document.class)
//
        //  User user = userService.getUserById(documentDto.getUserId());
        // document.setDocumentUser(user);

        document.setDocumentName(documentDto.getDocumentName());
        document.setDocumentSize(documentDto.getDocumentSize());
        document.setDocumentWordSize(documentDto.getDocumentWordSize());
        Document savedDocument = documentRepository.save(document);
        documentDto.setId(savedDocument.getId());
        documentDto.setTokenCost(calculateTokenCost(documentDto));
        return documentDto;

    }

    public int getWordCountFromPdf(InputStream pdfInputStream) throws IOException {
        try (PDDocument document = PDDocument.load(pdfInputStream)) {
            PDFTextStripper textStripper = new PDFTextStripper();
            String pdfText = textStripper.getText(document);
            int kelimeSayisi = countWords(pdfText);
            System.out.println("Document word count : " + kelimeSayisi );


            return kelimeSayisi;
        } catch (IOException e) {
            throw new IOException("PDF processing error: " + e.getMessage());
        }

    }
    public String calculateTokenCost(DocumentDto documentDto) {
       // Long documentSize = documentDto.getDocumentSize();
        Long documentWordSize = documentDto.getDocumentWordSize();
        Long tokenCost =  documentWordSize/10;
        System.out.println("NECESSARY TOKEN COST  : " + tokenCost );
        return "Token cost for the document: " + tokenCost;

    }

    private int countWords(String text) {
        String[] words = text.trim().split("\\s+");
        return words.length;
    }
    private Long KalanBakiyeHesap(Long total, Long tokenCost) {
        Long remainingBalance = KalanBakiyeHesap(total, tokenCost);

        remainingBalance = total-tokenCost;
        System.out.println("KALAN BAKİYENİZ : " + remainingBalance);
        return remainingBalance;
    }
}
