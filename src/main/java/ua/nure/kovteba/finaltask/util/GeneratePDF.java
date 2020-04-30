package ua.nure.kovteba.finaltask.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import ua.nure.kovteba.finaltask.entity.Flight;
import ua.nure.kovteba.finaltask.entity.Request;
import ua.nure.kovteba.finaltask.entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class GeneratePDF {

    private static Logger log = Logger.getLogger(GeneratePDF.class.getName());
    private static final Properties PROPERTIES = new Properties();
    private final String fileStorage;
    private static Font fontForHead;

    public GeneratePDF() {
        log.info("Constructor GeneratePDF");
        FileInputStream fileWithPropertiesForDB = null;
        try {
            fileWithPropertiesForDB = new FileInputStream("src/main/resources/application.properties");
            PROPERTIES.load(fileWithPropertiesForDB);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.fileStorage = PROPERTIES.getProperty("files.storage");
        BaseFont head = null;
        try {
            head = BaseFont.createFont("src/main/resources/ArialBlack.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        fontForHead = new Font(head);
    }

    public String generateFlightsByUserPDF(User user, List<Flight> flightList) throws DocumentException, IOException {
        Document document = new Document();
        String fileName = user.getPhoneNumber() + ".pdf";
        try {
            try {
                PdfWriter.getInstance(document, new FileOutputStream(fileStorage + fileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.open();
        document.add(new Paragraph("First name: " + user.getFirstName(), fontForHead));
        document.add(new Paragraph("Last name: " + user.getLastName(), fontForHead));
        document.add(new Paragraph("Phone number: " + user.getPhoneNumber(), fontForHead));
        PdfPTable table = new PdfPTable(5);
        table.setSpacingBefore(30);
        table.setSpacingAfter(10);
        addTableHeaderForFlights(table);
        addRowsToTableFlights(table, flightList);
        try {
            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
        return fileName;
    }

    public String generateApproveByUserPDF(User userApproved, User driver, Request request, Flight flight) throws DocumentException, IOException {
        Document document = new Document();
        String fileName = driver.getPhoneNumber() + "approved.pdf";
        System.out.println("FILE STORE : " + fileName);
        System.out.println("FILE STORE : " + fileStorage);
        try {
            try {
                PdfWriter.getInstance(document, new FileOutputStream(fileStorage + fileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.open();

        PdfPTable tableHead = new PdfPTable(2);
        tableHead.getDefaultCell().setBorder(0);
        tableHead.setSpacingAfter(50);
        tableHead.getWidthPercentage();
        tableHead.addCell(new Paragraph("Driver", fontForHead));
        tableHead.addCell(new Paragraph("Approbator", fontForHead));
        tableHead.addCell(new Paragraph(driver.getPhoneNumber(), fontForHead));
        tableHead.addCell(new Paragraph(userApproved.getPhoneNumber(), fontForHead));
        tableHead.addCell(new Paragraph(driver.getFirstName() + " " + driver.getLastName(), fontForHead));
        tableHead.addCell(new Paragraph(userApproved.getFirstName() + " " + userApproved.getLastName(), fontForHead));

        try {
            document.add(tableHead);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        document.add(new Paragraph("Request:   #" + request.getId(), fontForHead));
        PdfPTable tableRequest = new PdfPTable(6);
        tableRequest.setSpacingBefore(15);
        tableRequest.setSpacingAfter(30);
        addRowsToTableRequest(tableRequest, request);
        try {
            document.add(tableRequest);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        document.add(new Paragraph("Flight:   #" + flight.getId(), fontForHead));
        PdfPTable tableFlight = new PdfPTable(5);
        tableFlight.setSpacingBefore(15);
        tableFlight.setSpacingAfter(30);
        addTableHeaderForFlights(tableFlight);
        List<Flight> list = new ArrayList<>();
        list.add(flight);
        addRowsToTableFlights(tableFlight, list);

        try {
            document.add(tableFlight);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        Image image = Image.getInstance(fileStorage + "Approved.png");
        image.scaleAbsoluteWidth(135);
        image.scaleAbsoluteHeight(135);
        document.add(image);
        document.close();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    private static void addTableHeaderForFlights(PdfPTable table) {
        Stream.of("#", "Flight number", "Start date", "End date", "Status")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private static void addRowsToTableFlights(PdfPTable table, List<Flight> flightList) {
        int index = 0;
        for (Flight flight : flightList) {
            table.addCell(String.valueOf(++index));
            table.addCell(flight.getFlightNumber());
            table.addCell(String.valueOf(flight.getStartDate().toLocalDate()));
            table.addCell(String.valueOf(flight.getEndDate().toLocalDate()));
            table.addCell(flight.getFlightStatus().getStatusValue());
        }
    }

    private static void addRowsToTableRequest(PdfPTable table, Request request) {
        Stream.of("Car Class", "Load", "Air", "Luggage", "Navigator", "Seats")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
        table.addCell(request.getCarClass().getClassValue());
        table.addCell(String.valueOf(request.getLoadCapacity()));
        table.addCell(String.valueOf(request.getAirConditioning()));
        table.addCell(String.valueOf(request.getLuggageCompartment()));
        table.addCell(String.valueOf(request.getNavigator()));
        table.addCell(String.valueOf(request.getSeats()));
    }


}
