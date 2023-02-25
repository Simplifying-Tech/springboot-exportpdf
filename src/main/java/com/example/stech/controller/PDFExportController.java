package com.example.stech.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.stech.service.PDFGeneratorService;

@Controller
public class PDFExportController {
    @Autowired
    PDFGeneratorService pdfGeneratorService;
 
    @GetMapping("/openpdf/export")
    public void createPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
 
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
 
        pdfGeneratorService.export(response);
    }
}
