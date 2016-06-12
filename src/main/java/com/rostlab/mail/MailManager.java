package com.rostlab.mail;

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Longes on 12.06.2016.
 */
public class MailManager {

    public static void ReadMail() throws MessagingException, IOException {
        //
        // 1. Setup properties for the mail session.
        //
        Properties props = new Properties();
        props.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.imap.socketFactory.fallback", "false");
        props.put("mail.imap.socketFactory.port", "993");
        props.put("mail.imap.port", "993");
        props.put("mail.imap.host", "rostlab.informatik.tu-muenchen.de");
        props.put("mail.imap.user", "Agaltsev");
        props.put("mail.imap.password", "Agaltsev");
        props.put("mail.store.protocol", "imap");

        //
        // 2. Creates a javax.mail.Authenticator object.
        //
        Authenticator auth = null;

        //
        // 3. Creating mail session.
        //
        Session session = Session.getDefaultInstance(props, auth);

        //
        // 4. Get the IMAP store provider and connect to the store.
        //
        Store store = session.getStore("imap");
        store.connect("rostlab.informatik.tu-muenchen.de", "Agaltsev", "superboy");

        //
        // 5. Get folder and open the INBOX folder in the store.
        //
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        //
        // 6. Retrieve the messages from the folder.
        //
        Message[] messages = inbox.getMessages();
        for (Message message : messages) {
            message.writeTo(System.out);
        }

        //
        // 7. Close folder and close store.
        //
        inbox.close(false);
        store.close();
    }

}
