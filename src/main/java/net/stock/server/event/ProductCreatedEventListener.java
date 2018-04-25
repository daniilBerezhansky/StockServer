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

    private List<String> emails = new LinkedList<>();

    private List<String> getEmails(String category) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "dany98dany");
            String sql = String.format("SELECT users.email " +
                         "FROM users " +
                         "INNER JOIN categories ON users.id = categories.user_id " +
                         "WHERE %s = TRUE AND users.id > 10;",category);
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                emails.add(rs.getString("email"));
            }
            System.out.println(emails);
        }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        catch(SQLException e){
                e.printStackTrace();
            }
            return emails;
        }


    @EventListener
    public void processProductCreatedEvent(ProductCreatedEvent event){

        //TODO send Email
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject("Stock");
        email.setText("New product arrived: "+event.getProduct().getProductName());
        List<String> em = getEmails(event.getProduct().getCategory());
        for(String mail : em){
            email.setTo(mail);
            mailSender.send(email);
        }


    }
}

