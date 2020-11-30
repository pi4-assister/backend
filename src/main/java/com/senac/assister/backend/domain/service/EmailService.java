package com.senac.assister.backend.domain.service;

import com.senac.assister.AssisterApplication;
import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.enumeration.EmailSubjects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class EmailService {

    @Value("${GOOGLE_EMAIL}")
    private String ASSISTER_EMAIL;

    @Value("${GOOGLE_EMAIL_NAME}")
    private String ASSISTER_NAME;

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendHtmlEmail(Customer customer, EmailSubjects subjects) {
        MimeMessage mail = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, "UTF-8");

        String htmlFile = getHtmlFile(subjects);
        String htmlContent = decodeHtml(htmlFile);

        htmlContent = replaceEmailFlags(customer, htmlContent, subjects);

        try {
            helper.setFrom(ASSISTER_EMAIL, ASSISTER_NAME);
            helper.setTo(customer.getEmail());
            helper.setSubject(subjects.toString());
            helper.setText(htmlContent, true);

            mailSender.send(mail);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    private String replaceEmailFlags(Customer customer, String htmlContent, EmailSubjects subjects) {
        switch (subjects) {
            case CREATE_USER:
                return htmlContent.replace("#customer_name#", customer.getFullName());
            case FORGOT_PASSWORD:
                return htmlContent.replace("#forgot_code#", customer.getForgetPasswordCode());
            default:
                return "";
        }
    }

    @Async
    public void sendServiceHtmlEmail(com.senac.assister.backend.domain.entity.Service service, EmailSubjects subjects) {
        MimeMessage mail = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, "UTF-8");

        String htmlFile = getHtmlFile(subjects);
        String htmlContent = decodeHtml(htmlFile);

        htmlContent = replaceServiceEmailFlags(service, htmlContent, subjects);
        try {
            helper.setFrom(ASSISTER_EMAIL, ASSISTER_NAME);
            helper.setTo(service.getClientCustomer().getEmail());
            helper.setSubject(subjects.toString());
            helper.setText(htmlContent, true);

            mailSender.send(mail);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    private String replaceServiceEmailFlags(com.senac.assister.backend.domain.entity.Service service, String htmlContent, EmailSubjects subjects) {
        switch (subjects) {
            case QUOTE:
                return htmlContent
                        .replace("#assister_name#", service.getAssisterCustomer().getFullName())
                        .replace("#assister_photo_url#", service.getAssisterCustomer().getPhotoUrl())
                        .replace("#price#", Double.toString(service.getTotalPrice()))
                        .replace("#disabilities#", "Deficiências múltiplas e cuidado com idosos.");
            case SERVICE_IN_PROGRESS: return htmlContent;
            default:
                return "";
        }
    }

    private String decodeHtml(String htmlName) {
        InputStream in = AssisterApplication.class.getResourceAsStream(htmlName);

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        return reader.lines().collect(Collectors.joining());
    }

    private String getHtmlFile(EmailSubjects subjects) {
        String path = "/static/templates";

        switch (subjects) {
            case CREATE_USER:
                return path + "/hiring.html";
            case FORGOT_PASSWORD:
                return path + "/forgot_password.html";
            case QUOTE:
                return path + "/quote.html";
            case SERVICE_IN_PROGRESS:
                return path + "/service_in_progress.html";
            default:
                return "";
        }
    }
}