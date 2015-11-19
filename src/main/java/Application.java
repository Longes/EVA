import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Alexander Galtsev on 15.11.2015.
 */

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

/*public class MainApp {
    ApplicationContext context =
            new ClassPathXmlApplicationContext("Beans.xml");

    UniProt obj = (UniProt) context.getBean("uniprot");
    ParameterNameValue[] params = new ParameterNameValue[] {
            new ParameterNameValue("from", "ACC"),
            new ParameterNameValue("to", "P_REFSEQ_AC"),
            new ParameterNameValue("format", "tab"),
            new ParameterNameValue("query", "P13368 P20806 Q9UM73 P97793 Q17192")
    };
    obj.translate("mapping", params);
}*/