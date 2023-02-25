package com.example.stech.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PDFGeneratorService {
    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
 
        document.open();
        Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD);
        fontHeader.setSize(22);
 
        Paragraph headerParagraph = new Paragraph("## PDF Heading ##", fontHeader);
        headerParagraph.setAlignment(Paragraph.ALIGN_CENTER);
 
        Font fontParagraph = FontFactory.getFont(FontFactory.TIMES);
        fontParagraph.setSize(14);
 
        Paragraph pdfParagraph = new Paragraph("", fontParagraph);
        pdfParagraph.setAlignment(Paragraph.ALIGN_LEFT);
        
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
        for(int i=0;i<=15;i++) { 
        pdfParagraph.add("Row: "+i
                  +" Time: "+dateFormatter.format(new Date()) +"\n"); 
        }
         
        document.add(headerParagraph);
        document.add(pdfParagraph);
        document.close();
    }
}
