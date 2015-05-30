/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.misc;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.photoshop.controllers.OrderController;
import com.photoshop.models.order.Order;
import com.photoshop.models.orderrow.OrderRow;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 *
 * @author bart
 */
public class Pdfgenerator {
    private Environment env;
    private static Font catFont;
    private static Font subFont;
    private static Font smallBold;
    private static Font subtitel;
    private double totaalprijs = 0;
    private Order order;
    
    public Pdfgenerator(Order order,Environment env)
    {
        this.env = env;
        this.order = order;
        String FILE = "c:/order1.pdf"; //order generate moet nog gemaakt worden
        catFont = new Font(Font.FontFamily.HELVETICA, 18,
                Font.BOLD);
        subtitel = new Font(Font.FontFamily.HELVETICA, 14,
                Font.BOLD);
        subFont = new Font(Font.FontFamily.HELVETICA, 12,
                Font.NORMAL);
        smallBold = new Font(Font.FontFamily.HELVETICA, 12,
                Font.BOLD);

        try {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addTitlePage(document);
            //addContent(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMetaData(Document document) {
        document.addTitle("Order1"); //Moet order nummer uit database worden
        document.addAuthor("fotograaf1"); // naam fotograaf
        document.addCreator("Photowinkel"); // aangeboden door
    }

    private void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);

        Image Logo = null;
        try {
            Logo = Image.getInstance(env.getProperty("logo") + "Photoshop_black.png");
            Logo.scaleAbsolute(200, 100);
        } catch (BadElementException | IOException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SimpleDateFormat simpledatafo = new SimpleDateFormat("dd/MM/yyyy");     
        preface.add(Logo);
        Paragraph datum = new Paragraph("Datum: " + simpledatafo.format(new Date()),smallBold);
        datum.setAlignment(Element.ALIGN_RIGHT);
        preface.add(datum);
        Paragraph Factuurnummer = new Paragraph("Factuurnummer: " + order.getId(),smallBold);
        Factuurnummer.setAlignment(Element.ALIGN_RIGHT);
        preface.add(Factuurnummer);
        addEmptyLine(preface, 1);
        
        //Aanmaken van de bedrijfs gegevens
        preface.add(new Paragraph("Bedrijf:", subtitel));
        preface.add(new Paragraph("Rachelsmolen 1", subFont));
        preface.add(new Paragraph("5612MA Eindhoven", subFont)); //order nummer ingelezen worde
        preface.add(new Paragraph("Rekening: 165947888", subFont));
        preface.add(new Paragraph("Bank: Paypal", subFont));
        addEmptyLine(preface, 1);
        
        //Aanmaken van de bestellende persoons gegevens
        preface.add(new Paragraph("Aan:", subtitel));
        preface.add(new Paragraph(order.getInvoiceaddress().getKlantnaam(), subFont)); //order nummer ingelezen worde
        preface.add(new Paragraph(order.getInvoiceaddress().getAdres(), subFont));
        preface.add(new Paragraph(order.getInvoiceaddress().getPostcode() + " " + order.getInvoiceaddress().getWoonplaats(), subFont));
        preface.add(new Paragraph(order.getInvoiceaddress().getTelefoonnummer(), subFont));
        addEmptyLine(preface, 1);
        
        //Aanmaken van de start zin 
        preface.add(new Paragraph("Geachte heer" + " " + order.getInvoiceaddress().getKlantnaam(), subtitel));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Hiebij ontvang u het betaaloverzicht voor de bestelde producten.", subFont));
        addEmptyLine(preface, 1);
        //Aanmaken van de betaal tabel
        createTable(preface);
        //Overzicht bwt bedrag
        addEmptyLine(preface, 1);
        Paragraph btw = new Paragraph("Btw bedrag: " + "€ " + String.format( "%.2f",(this.totaalprijs / 100) * 19),subFont);
        btw.setAlignment(Element.ALIGN_RIGHT);
        preface.add(btw);
        //Overzicht Totaalbedrag
        Paragraph Totaalbedrag = new Paragraph("Totaal bedrag: " + "€ " + String.format( "%.2f",this.totaalprijs),subtitel);
        Totaalbedrag.setAlignment(Element.ALIGN_RIGHT);
        preface.add(Totaalbedrag);
        addEmptyLine(preface, 1);
        
        //Toevoegen footerzin
        Paragraph footer = new Paragraph("Wij verzoeken vriendelijk het verschuldigde bedrag binnen 14 dagen over te maken onder vermelding van het factuurnummer.",subFont);
        footer.setAlignment(Element.ALIGN_CENTER);
        preface.add(footer);
        document.add(preface);
    }
  
    private void creatCell(String cellnaam,PdfPTable table,boolean header)
    {
        if (header == true) {
            Font font = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
            PdfPCell c1 = new PdfPCell(new Phrase(cellnaam, font));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setBackgroundColor(new BaseColor(0, 121, 182));
            c1.setMinimumHeight(20);
            table.addCell(c1);
        }
        if (header == false) {
            PdfPCell c1 = new PdfPCell(new Phrase(cellnaam));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
        }
    }
    
    private void createTable(Paragraph preface)
            throws BadElementException, DocumentException {
        // create header cell
        PdfPTable table = new PdfPTable(5);
        
        // set the width of the table to 100% of page
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.6f,0.4f, 1.4f, 0.8f,0.8f});
        table.setHeaderRows(1);
        creatCell("Aantal",table,true);
        creatCell("PhotoID",table,true);
        creatCell("Beschrijving",table,true);
        creatCell("Prijs per eenheid",table,true);
        creatCell("Totaal",table,true);

        for(OrderRow row : order.getOrderRows())
        {
            creatCell(row.getAantal() + "", table, false);
            creatCell(row.getPhoto_id() + "", table, false);
            creatCell(row.getProduct().getName(), table, false);
            creatCell("€ " + row.getProductprice(), table, false);
            
            double totaalprijsproduct = row.getProductprice() * row.getAantal();
            creatCell("€ " + String.format( "%.2f",totaalprijsproduct), table, false);
            totaalprijs = totaalprijs + totaalprijsproduct;
        }
        preface.add(table);
    }
    
    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    
}
