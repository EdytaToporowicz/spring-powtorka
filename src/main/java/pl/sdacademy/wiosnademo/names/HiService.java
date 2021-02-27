package pl.sdacademy.wiosnademo.names;

import org.springframework.stereotype.Component;

@Component
public class HiService implements BaseType{
    @Override
    public void hello() {
        System.out.println("hi from HiService");
    }
}
