package com.tokenforpdf.token.controller;

import com.tokenforpdf.token.dto.DocumentDto;
import com.tokenforpdf.token.service.DocumentService;
import com.tokenforpdf.token.service.PdfService;
import com.tokenforpdf.token.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/v1/document")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;
    private final PdfService pdfService;
    private final UserService userService;
    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile file, @RequestParam(name = "userId") Long id) {
        try {
            int wordCount = documentService.getWordCountFromPdf(file.getInputStream());

            userService.getUserById(id);
            DocumentDto documentDto = new DocumentDto();
            documentDto.setDocumentName(file.getOriginalFilename());
            documentDto.setDocumentSize((long)file.getSize());
            documentDto.setDocumentWordSize((long) wordCount);

            System.out.println(documentDto.getDocumentSize()+ " " +  documentDto.getDocumentName());

            DocumentDto savedDocument = documentService.uploadDocument(documentDto);
            return new ResponseEntity<>("Document uploaded successfully. Token cost for the document: " +
                    savedDocument.getTokenCost(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error processing the uploaded PDF: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
