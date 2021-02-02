package com.pdf.demo.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pdf.demo.model.City;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class GeneratePdfReport {
	private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);

    public static ByteArrayInputStream citiesReport(List<City> cities) {
        Document document = new Document(); //create document object
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfPTable table = new PdfPTable(3); //declare table variable and number of collums
            table.setWidthPercentage(60); //Declare white space
            table.setWidths(new int[]{1, 3, 3}); //declare cell sizes

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD); //declare font type and style

            PdfPCell hcell; //declare collum variable
            hcell = new PdfPCell(new Phrase("Id", headFont)); //name collum
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER); //set text alignment
            table.addCell(hcell); //add to table

            hcell = new PdfPCell(new Phrase("Name", headFont)); //name collum
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER); //set text alignment
            table.addCell(hcell); //add to table

            hcell = new PdfPCell(new Phrase("Population", headFont)); //name collum
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER); //set text alignment
            table.addCell(hcell);  //add to table
            
            //create loop to cycle through stored data array to dynamically populate table
            for (City city : cities) {
                PdfPCell cell; //declare cells listed by row

                cell = new PdfPCell(new Phrase(city.getId().toString())); //populate cell
                //set alignment
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell); //add to table

                cell = new PdfPCell(new Phrase(city.getName())); //populate cell
                //set alignment
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell); //add to table

                cell = new PdfPCell(new Phrase(String.valueOf(city.getPopulation()))); //populate cell
                //set alignment
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell); //add to table
            }

            PdfWriter.getInstance(document, out); //set pdf to stream data to the document object
            document.open();
            document.add(table); //load table into document

            document.close(); //close stream to document.

        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
