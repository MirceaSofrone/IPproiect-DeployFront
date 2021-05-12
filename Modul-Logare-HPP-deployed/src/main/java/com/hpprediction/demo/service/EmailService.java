package com.hpprediction.demo.service;

import com.hpprediction.demo.repository.EmailSender;
import freemarker.template.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(EmailService.class);

    private static final String GMAIL_SENDER_ADDRESS = "housepredictionlogin@gmail.com";
    public static final String FAILED_TO_SEND_EMAIL = "Failed to send email";
    private final JavaMailSender mailSender;

    @Autowired
    private Configuration freemarkerConfig;

    @Override
    @Async
    public void send(String to,
                     String subject,
                     String templatePath,
                     Map<String, Object> model) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(GMAIL_SENDER_ADDRESS);
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/EmailTemplates");
            Template mailTempalate = freemarkerConfig.getTemplate(templatePath);
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(mailTempalate, model);

            helper.setText(text, true);

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            LOGGER.error(FAILED_TO_SEND_EMAIL, e);
            throw new IllegalStateException(FAILED_TO_SEND_EMAIL);
        } catch (IOException | TemplateException e) {
            LOGGER.error(String.valueOf(e));
        }
    }
}
