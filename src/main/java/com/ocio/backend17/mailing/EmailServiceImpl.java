package com.ocio.backend17.mailing;

import com.ocio.backend17.config.IConfigImpl;
import com.ocio.backend17.entities.Assistants;
import com.ocio.backend17.entities.Events;
import com.ocio.backend17.entities.Users;
import com.ocio.backend17.services.AssistantImpl;
import com.ocio.backend17.services.UsersImpl;
import com.ocio.backend17.utils.DateFormatterSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl {
    @Autowired
    IConfigImpl iConfig;
    @Autowired
    DateFormatterSQL dateFormatterSQL;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    UsersImpl usersImpl;
    @Autowired
    AssistantImpl assistantImpl;

    public void newEventToOrganizer(String organizer, Events event) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("Ocio Open <" + iConfig.getGoogleMail() + ">");
        helper.setTo(organizer);
        helper.setSubject("Nuevo evento");
        helper.setText("<p>¡Enhorabuena " + organizer + "! has creado un nuevo evento.</p><b>Haz click <a href="
                + iConfig.getFrontendHost() + "/eventsbydate/"
                + dateFormatterSQL.timestampSQLtoShortDateString(event.getDate()) + ">aquí</a> para verlo</b>", true);

        emailSender.send(message);
    }

    public void newEventToAllUsers(String organizer, Events event) throws MessagingException {

        List<Users> users = usersImpl.getAll();
        for (Users user : users) {
            if (!(user.getEmail().equals(organizer))) {
                MimeMessage message = emailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                helper.setFrom("Ocio Open <" + iConfig.getGoogleMail() + ">");
                helper.setTo(user.getEmail());
                helper.setSubject("Nuevo evento " + event.getTittle());
                helper.setText("<p>" + organizer + " ha creado un nuevo evento.</p><b>Haz click <a href="
                                + iConfig.getFrontendHost() + "/eventsbydate/"
                                + dateFormatterSQL.timestampSQLtoShortDateString(event.getDate()) + ">aquí</a> para verlo</b>",
                        true);
                emailSender.send(message);
            }
        }
    }

    public void updatedEventToOrganizer(String organizer, Events event) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("Ocio Open <" + iConfig.getGoogleMail() + ">");
        helper.setTo(organizer);
        helper.setSubject("Cambios en el evento " + event.getTittle());
        helper.setText("<p>¡Enhorabuena " + organizer + "! has hecho cambios en el evento " + event.getTittle()
                + " con éxito.</p><b>Haz click <a href=" + iConfig.getFrontendHost() + "/eventsbydate/"
                + dateFormatterSQL.timestampSQLtoShortDateString(event.getDate()) + ">aquí</a> para verlo</b>", true);
        emailSender.send(message);
    }

    public void updateEventToAllAssistants(String organizer, Events event) throws MessagingException {
        List<Assistants> assistants = assistantImpl.findByEventAndAttendance(event.getEvent_id(), true);
        for (Assistants assistant : assistants) {
            if (!(assistant.getAssistant().equals(organizer))) {
                MimeMessage message = emailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                helper.setFrom("Ocio Open <" + iConfig.getGoogleMail() + ">");
                helper.setTo(assistant.getAssistant());
                helper.setSubject("Nuevo evento " + event.getTittle());
                helper.setText("<p>" + organizer + " ha creado un nuevo evento.</p><b>Haz click <a href="
                                + iConfig.getFrontendHost() + "/eventsbydate/"
                                + dateFormatterSQL.timestampSQLtoShortDateString(event.getDate()) + ">aquí</a> para verlo</b>",
                        true);
                emailSender.send(message);
            }
        }
    }

    public void deletedEventToOrganizer(String organizer, Events event) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("Ocio Open <" + iConfig.getGoogleMail() + ">");
        helper.setTo(organizer);
        helper.setSubject("Cambios en el evento " + event.getTittle());
        helper.setText("<p>" + organizer + ", has eliminado el evento " + event.getTittle() + " con éxito.</p>", true);
        emailSender.send(message);
    }

    public void deletedEventToAllAssistants(String organizer, Events event, List<Assistants> assistants)
            throws MessagingException {
        for (Assistants assistant : assistants) {
            if (!(assistant.getAssistant().equals(organizer))) {
                MimeMessage message = emailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                helper.setFrom("Ocio Open <" + iConfig.getGoogleMail() + ">");
                helper.setTo(assistant.getAssistant());
                helper.setSubject("Evento " + event.getTittle() + " eliminado");
                helper.setText("<p>" + organizer + " ha  eliminado el evento " + event.getTittle() + ".</p>", true);
                emailSender.send(message);
            }
        }
    }

    public void sendUsersReportTo(String email, File file)
            throws MessagingException {


        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("Ocio Open <" + iConfig.getGoogleMail() + ">");
        helper.setTo(email);
        helper.setSubject("Informe de usuarios");
        helper.setText("<h3>Informe de usuarios adjunto.</h3>", true);
        helper.addAttachment("Informe_de_usuarios.pdf", file);
        emailSender.send(message);


    }
}
