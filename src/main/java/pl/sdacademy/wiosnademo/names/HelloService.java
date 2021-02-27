package pl.sdacademy.wiosnademo.names;

import org.springframework.stereotype.Component;

@Component
public class HelloService implements BaseType {
    @Override
    public void hello() {
        System.out.println("hello from HelloService");
    }
}
