package com.pranavv51.microservice.pdfservice.pdfmicroservice.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;


@Service
public class PdfService {

    private int getLength(LinkedList<LinkedList<String>> tempList){
        int length=0;

        for(LinkedList<String> listofStrings: tempList){
            return listofStrings.size();
        }

        return 0;
    }

    private PdfPTable getpdfTable(LinkedList<LinkedList<String>> infoList){

        PdfPTable tableInst = new PdfPTable(getLength(infoList));
        PdfPCell cell = new PdfPCell();

        Font cellFontBold = new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD));
        cellFontBold.setSize(16);
        Font cellFont = new Font(FontFactory.getFont(FontFactory.HELVETICA));
        cellFont.setSize(16);
        int r=0;

        for(LinkedList<String> valuesOfTuples : infoList){
            //first row -> bold
            Iterator<String> iter = valuesOfTuples.iterator();
            while(iter.hasNext()){
                r++;
                if(r>=0 && r<=getLength(infoList)){
                    cell.setPhrase(new Phrase(iter.next(),cellFontBold));
                    tableInst.addCell(cell);
                }else{
                    cell.setPhrase(new Phrase(iter.next(),cellFont));
                    tableInst.addCell(cell);
                }
            }
        }

    return tableInst;
    }


    public byte[] generatePdf(LinkedList<LinkedList<String>> infoList) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document doc = new Document(PageSize.A4);

        try {
            PdfWriter.getInstance(doc, byteArrayOutputStream);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

        doc.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        titleFont.setSize(17);

        Paragraph title = new Paragraph("FileVista!", titleFont);
        title.setAlignment(Paragraph.ALIGN_CENTER);

        Paragraph spaceBlankLine = new Paragraph("\n", titleFont);
        spaceBlankLine.setAlignment(Paragraph.ALIGN_CENTER);

        try {
            doc.add(title);
            doc.add(spaceBlankLine);
            doc.add(getpdfTable(infoList));
            doc.close();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

        return byteArrayOutputStream.toByteArray();
    }
}

