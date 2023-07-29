package com.tokenforpdf.token.service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class PdfService {

    public int getWordCountFromPdf(InputStream pdfInputStream) throws IOException {
        try (PDDocument document = PDDocument.load(pdfInputStream)) {
            PDFTextStripper textStripper = new PDFTextStripper();
            String pdfText = textStripper.getText(document);
            return countWords(pdfText);
        } catch (IOException e) {
            throw new IOException("PDF processing error: " + e.getMessage());
        }
    }

    private int countWords(String text) {
        String[] words = text.trim().split("\\s+");
        return words.length;
    }
}
