package pl.sdacademy.wiosnademo.names;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary        //mamy dwie implementacje(dwa beany) interfejsu BaseType
// i nie wie który wziąć = weźmie tą z adnotacją Primary
@Component
public class HelloService implements BaseType {
    @Override
    public void hello() {
        System.out.println("hello from HelloService");
    }
}
