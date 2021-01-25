import exceptions.ConnectionException;
import exceptions.MessageException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class SendMessage {
    /**
     * метод отправляет письмо на email с полученными результатами
     *
     * @param mailFrom           - email с которого отправлено письмо
     * @param mailTO             - email на который отправлено письмо
     * @param password           - пароль для email с которого отправлено письмо
     * @param appearedWebsite    - массив появившихся страниц
     * @param disappearedWebsite - массив удаленных старниц
     * @param changedWebsite     - массив измененых страниц
     */
    public void postMessage(String mailFrom, String mailTO, String password,
                            List<String> appearedWebsite,
                            List<String> disappearedWebsite,
                            List<String> changedWebsite) {
        final Properties properties = new Properties();
        try {
            properties.load(SendMessage.class.getClassLoader().getResourceAsStream("mail.properties"));
        } catch (IOException ioException) {
            throw new ConnectionException("mistake in load properties", ioException);
        }
        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);
        try {
            message.setFrom(new InternetAddress(mailFrom));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTO));
            String textMail = "Здравствуйте, дорогая и.о. секретаря\n" +
                    "\n" +
                    "За последние сутки во вверенных Вам сайтах произошли следующие изменения:\n" +
                    "\n" +
                    "\n" +
                    "Исчезли следующие страницы:" + disappearedWebsite.toString() + "\n" +
                    "\n" +
                    "Появились следующие новые страницы " + appearedWebsite.toString() + "\n" +
                    "\n" +
                    "Изменились следующие страницы " + changedWebsite.toString() + "\n" +
                    "\n" +
                    "\n" +
                    "С уважением,\n" +
                    "\n" +
                    "автоматизированная система\n" +
                    "\n" +
                    "мониторинга.";
            message.setText(textMail);

            Transport transport = mailSession.getTransport();
            transport.connect(null, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException messagingException) {
            throw new MessageException("mistake in send message", messagingException);
        }
    }
}
