package com.rostlab.eva;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Longes on 19.11.2015.
 */
public class UniProtController {

    @RequestMapping("/request")
    public void request(@RequestParam(value="id") String id, @RequestParam(value = "format", defaultValue = "xml") String format) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Beans.xml");
        try {
            UniProt.getProtein(id, format);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
