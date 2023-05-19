package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.DBContextUser;
import model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.TrustManagerFactory;

public class SendEmailCommand implements ICommand{
    private static final DBContextUser dbContextUser = new DBContextUser();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String execute(String data) {
        User user = new User();
        try {
            user = objectMapper.readValue(data, User.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "Smtp.gmail.com"); // Replace with your SMTP server host
        properties.put("mail.smtp.port", "587"); // Replace with the appropriate SMTP port
        properties.put("mail.smtp.auth", "true"); // Enable authentication if required
        properties.put("mail.smtp.starttls.enable", "true"); // Enable TLS
        properties.put("mail.debug", "true");

        // Create a Session object
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mihneasebastianburlacu@gmail.com", "Mihnea_2001"); // Replace with your email and password
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mihneasebastianburlacu@gmail.com")); // Replace with your email
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("mihneasebastianburlacu@yahoo.com")); // Replace with the recipient's email
            message.setSubject("Your account was modified");
            message.setText("Your account was modified.\nYour new details are: " + user.getUsername() + ":" + user.getPassword());

            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully!");

            return "sent";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
