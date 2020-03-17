package com.swevolution.zonemanager.business.messaging;

/*
 * Copyright 2015 Rxkx.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import javax.ejb.Stateless;

/**
 *
 * @author Rxkx
 */
@Stateless
public class EmailManager {

    /*@Resource(lookup = "java:jboss/mail/SwEvolutionEmail")
    private Session mailSession;
    private static final Logger LOG = Logger.getLogger(EmailManager.class.getName());

    @Asynchronous
    public void sendEmail(String subject,
            String toEmail,
            String fromEmail,
            String body) {
        try {
            Message msg = new MimeMessage(mailSession);
            msg.setSubject(subject);
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail, toEmail));
            msg.setFrom(new InternetAddress(fromEmail, fromEmail));
            msg.setSentDate(new Date(System.currentTimeMillis()));
            Multipart mp = new MimeMultipart();
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(body, "text/html; charset=utf-8");
            htmlPart.setHeader("Content-Type", "text/html; charset=UTF-8");
            mp.addBodyPart(htmlPart);
            msg.setContent(mp);
            Transport.send(msg);
        } catch (Exception e) {
        }
    }

    private String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }*/
}
