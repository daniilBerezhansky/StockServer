package net.stock.server.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


@Component
public class ProductCreatedEventListener {

    @Autowired
    private JavaMailSender mailSender;

    List<String> emails = new LinkedList<>();

    public List<String> getEmails() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "dany98dany");
            String sql = "SELECT email FROM users WHERE id > 10";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                emails.add(rs.getString("email"));
            }
        }catch(ClassNotFoundException e){
                e.printStackTrace();
            } catch(SQLException e){
                e.printStackTrace();
            }
            return emails;
        }


    @EventListener
    public void processProductCreatedEvent(ProductCreatedEvent event){
     /*   System.out.println("================================\n"+
                            "Event received: " + event.getProduct().toString() +
                            "\n===============================");*/
        //TODO send Email
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject("Stock");
        email.setText("New product arrived: "+event.getProduct().getProductName().toString());
        List<String> em = getEmails();
        for(String mail : em){
            email.setTo(mail);
            mailSender.send(email);
        }


    }
}

