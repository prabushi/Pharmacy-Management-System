/*
 * To print the bill, here it represented printing bill by crating an PDF of the bill
 */
package UserGUI;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Prabushi
 */
public class PrintBill {

    private static String FILE = "c:/Users/DELL/Documents/NetBeansProjects/Pharmacy/Bill/Bill.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    //create a PDF
    public void print(String customer) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addContent(document, customer);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //add default data to the PDF
    private static void addMetaData(Document document) {
        document.addTitle("Customer Bill");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Prabushi");
        document.addCreator("Prabushi");
    }

//add content to the PDF
    private static void addContent(Document document, String customer) throws DocumentException {
        Paragraph preface = new Paragraph();
        preface.add(new Paragraph("Udupila Pharmacy", catFont));

        addEmptyLine(preface, 1);
        preface.add(new Paragraph("No. 271/E, Weboda Rd, Udupila,",
                smallBold));
        preface.add(new Paragraph("Delgoda",
                smallBold));
        preface.add(new Paragraph("+9411 3148906",
                smallBold));
        preface.add(new Paragraph("Billing at: " + System.getProperty("user.name") + ", " + new Date(),
                smallBold));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Customer: " + customer,
                smallBold));
        Bill bill = Bill.getInstance();
        preface.add(new Paragraph("Bill ID: " + bill.id,
                smallBold));


        document.add(preface);

        String total = Float.toString(bill.getTotal());
        Anchor anchor = new Anchor("Total: " + total, catFont);
        anchor.setName("Total");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Payment", subFont);
        Section subCatPart = catPart.addSection(subPara);
        Payment p = Payment.getInstance();
        String ca = p.tcash.getText();
        float cash = Float.parseFloat(ca);
        float tot = bill.getTotal();
        float bal = cash - tot;
        String balance = Float.toString(bal);
        subCatPart.add(new Paragraph("Cash: " + ca));
        subCatPart.add(new Paragraph("Balance: " + balance));

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);

        // add a table
        createTable(subCatPart);

        // now add all this to the document
        document.add(catPart);


    }

    //create a table in the PDF
    private static void createTable(Section subCatPart)
            throws BadElementException {
        PdfPTable table = new PdfPTable(4);

        PdfPCell c1 = new PdfPCell(new Phrase("Item"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Price"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Quantity"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Total"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        Bill bill = Bill.getInstance();
        ArrayList<Item> list = bill.billingList;

        for (int i = 0; i < list.size(); i++) {
            Item item = list.get(i);
            table.addCell(item.getName());
            table.addCell(item.getPrice());
            table.addCell(item.getQuantity());
            table.addCell(item.getTotal());
        }
        subCatPart.add(table);

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
