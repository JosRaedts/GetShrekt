/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.models.UserType;
import com.photoshop.models.admin.AdminDao;
import com.photoshop.models.order.Order;
import com.photoshop.models.order.OrderDao;
import com.photoshop.models.photographer.PhotographerDao;
import com.photoshop.models.student.Student;
import com.photoshop.models.student.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.photoshop.models.product.ProductDao;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Bart
 */
@RequestMapping("/order")
@Controller
public class OrderController extends AbstractController  {
    
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private PhotographerDao photographerDao;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private RedirectAttributes redirectAttributes;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;

    private static Font catFont;
    private static Font redFont;
    private static Font subFont;
    private static Font smallBold;
    
    //http://www.vogella.com/tutorials/JavaPDF/article.html infromatie pdf creator
    
    public OrderController()
    {
    }
    
    @RequestMapping(value = "/startpage", method = RequestMethod.GET)
    public String list(ModelMap map, HttpServletRequest request) {
        if (this.authenticate(UserType.STUDENT)) 
        {
            return "order/startpage";
        }
        return "redirect:../";
    }
    
    @RequestMapping(value = "/orderoverzicht", method = RequestMethod.GET)
    public String Monitoring(ModelMap map, HttpServletRequest request) {
        if (this.authenticate(UserType.ADMIN)) 
        {
            map.put("orders", this.orderDao.getList());
            System.out.println("Yay :)");
            return "order/orderoverzicht";
        }
        System.out.println("Yay :)");
        return "redirect:../";
    }
    
    @RequestMapping(value = "/detail/{OrderId:^[0-9]+$}", method = RequestMethod.GET)
    public String detail(ModelMap map, HttpServletRequest request, @PathVariable("OrderId") int id) {
        if (this.authenticate(UserType.ADMIN)) 
        {
            Order order = orderDao.getById(id);
            Student student = order.getStudent();
            map.put("order", order);
            map.put("student", student);
            map.put("productlist", productDao.getProductListPerOrder(id));
            return "order/detail";
        }
        return "redirect:../../";
    }
    
    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
    public String pdf(ModelMap map, HttpServletRequest request) throws IOException {
        if (this.authenticate(UserType.STUDENT)) {
            String FILE = "c:/order1.pdf"; //order generate moet nog gemaakt worden
            catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
                    Font.BOLD);
            redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                    Font.NORMAL, BaseColor.RED);
            subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
                    Font.BOLD);
            smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                    Font.BOLD);

            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(FILE));
                document.open();
                addMetaData(document);
                addTitlePage(document);
                addContent(document);
                document.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "order/startpage";
        }
        return "redirect:../";
    }
    
    private void addMetaData(Document document) {
        document.addTitle("Order1"); //Moet order nummer uit database worden
        document.addAuthor("fotograaf1"); // naam fotograaf
        document.addCreator("Photowinkel"); // aangeboden door
    }

    private void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("Photowinkel order 1", catFont)); //order nummer ingelezen worden
        
        
        //////// Probleem met inlezen van de foto nog oplossen
        String url = getClass().getClassLoader().getResource("img/Photoshop_black.png").toString(); 
        Image image = null;
        try {
            image = Image.getInstance(url);
        } catch (BadElementException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        preface.add(image);
        document.add(preface);
        // Start a new page
        //document.newPage();
    }

    private void addContent(Document document) throws DocumentException {
        Anchor anchor = new Anchor("First Chapter", catFont);
        anchor.setName("First Chapter");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Subcategory 1", subFont);
        Section subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Hello"));

        subPara = new Paragraph("Subcategory 2", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Paragraph 1"));
        subCatPart.add(new Paragraph("Paragraph 2"));
        subCatPart.add(new Paragraph("Paragraph 3"));

        // add a list
        createList(subCatPart);
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 5);
        subCatPart.add(paragraph);

        // add a table
        createTable(subCatPart);

        // now add all this to the document
        document.add(catPart);

        // Next section
        anchor = new Anchor("Second Chapter", catFont);
        anchor.setName("Second Chapter");

        // Second parameter is the number of the chapter
        catPart = new Chapter(new Paragraph(anchor), 1);

        subPara = new Paragraph("Subcategory", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("This is a very important message"));

        // now add all this to the document
        document.add(catPart);

    }

    private void createTable(Section subCatPart)
            throws BadElementException {
        PdfPTable table = new PdfPTable(3);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);
        PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 2"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 3"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        table.addCell("1.0");
        table.addCell("1.1");
        table.addCell("1.2");
        table.addCell("2.1");
        table.addCell("2.2");
        table.addCell("2.3");

        subCatPart.add(table);

    }

    private void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
