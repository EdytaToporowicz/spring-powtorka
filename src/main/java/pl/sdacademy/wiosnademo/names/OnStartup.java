package pl.sdacademy.wiosnademo.names;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class OnStartup implements CommandLineRunner {   //uruchamia taką klasę na starcie
    // kontekstu przed startem aplikacji
    //@Primary oznaczamy Bean o który nam chodzi (tu HelloService)

    private BaseType baseType;

    public OnStartup( BaseType baseType) {
        // public OnStartup(@Qualifier("hiService") BaseType baseType)
        // = drugi sposób nadania priorytetu beana,zamiast @Primary

        this.baseType = baseType;
    }

    @Override
    public void run(String... args) throws Exception {
        baseType.hello();
    }
}
