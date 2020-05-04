package ua.nure.kovteba.finaltask.util;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class EmailSender {

    private final String userName;
    private final String password;
    private final Properties properties = new Properties();
    private static final Properties PROPERTIES_DB = new Properties();
    private static final Properties PROPERTIES_APPLICATION = new Properties();
    private static String fileStorage;

    public EmailSender() {
        FileInputStream fileWithPropertiesForDB = null;
        FileInputStream fileWithPropertiesForDirectory = null;
        try {
            fileWithPropertiesForDB = new FileInputStream("src/main/resources/email.config.properties");
            fileWithPropertiesForDirectory = new FileInputStream("src/main/resources/application.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            PROPERTIES_DB.load(fileWithPropertiesForDB);
            PROPERTIES_APPLICATION.load(fileWithPropertiesForDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileStorage = PROPERTIES_APPLICATION.getProperty("files.storage");
        userName = PROPERTIES_DB.getProperty("mail.username");
        password = PROPERTIES_DB.getProperty("mail.password");
        properties.put("mail.smtp.auth", PROPERTIES_DB.getProperty("mail.smtp.auth"));
        properties.put("mail.smtp.starttls.enable", PROPERTIES_DB.getProperty("mail.smtp.starttls.enable"));
        properties.put("mail.smtp.host", PROPERTIES_DB.getProperty("mail.host"));
        properties.put("mail.smtp.port", PROPERTIES_DB.getProperty("mail.port"));
        properties.put("mail.transport.protocol", PROPERTIES_DB.getProperty("mail.protocol"));
    }

    public void send(String subject, String text, String fileName, String emailTo) {
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };

        Session session = Session.getInstance(properties, auth);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("kovteba@gmail.com"));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("emailTo"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("kovteba@gmail.com"));
            message.setSubject(subject);
            message.setText(text);

            Multipart multipart = new MimeMultipart();
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(new File(fileStorage + fileName));
            multipart.addBodyPart(attachmentBodyPart);
            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
