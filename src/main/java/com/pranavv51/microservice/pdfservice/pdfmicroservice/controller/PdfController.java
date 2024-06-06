package com.pranavv51.microservice.pdfservice.pdfmicroservice.controller;

import com.pranavv51.microservice.pdfservice.pdfmicroservice.service.PdfService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@RequestMapping(value="pdf-generator/")
@RestController
public class PdfController {

    //http://localhost:8080/pdf-generator/generate-pdf
    private final PdfService serviceInst;
    public PdfController(PdfService serviceInst) {
        this.serviceInst = serviceInst;
    }

    @PostMapping(value="generate-pdf")
    public ResponseEntity<byte[]> generatePdf(@RequestBody LinkedList<LinkedList<String>> infoList){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename("generated-pdf.pdf").build());
        byte[] pdfBytes = serviceInst.generatePdf(infoList);
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }



}

