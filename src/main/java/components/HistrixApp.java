package components;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@SpringBootApplication
@RestController

@EnableHystrix
public class HistrixApp {
    public static void main(String[] args) {
        SpringApplication.run(HistrixApp.class, args);
    }

    @RequestMapping(value = "/echo/{param}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallback_hello", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
    })
    public String echo (@PathVariable(name = "param") String param ){
        Random random=new Random();
        int intRandom= random.nextInt(999);
        System.out.println("IntRandom: "+intRandom);
        try {
            Thread.sleep(intRandom);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Echo... "+intRandom;
    }

    public String fallback_hello( String param  ){
        return "Sorry.. delayed in controller (1000)!!";
    }
}
