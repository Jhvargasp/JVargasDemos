package components;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.*;

public class HystrixCommandImpl extends HystrixCommand<String> {

    private String data;

    public HystrixCommandImpl(String p){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HystrixCommandImplServiceGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("PrimaryCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("PrimaryCommand"))
                .andCommandPropertiesDefaults(
                        // we default to a 600ms timeout for primary
                        HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(200)));
        this.data=p;
    }


    @Override
    protected String run() throws Exception {
        try {
            System.out.println("Is open: "+this.isCircuitBreakerOpen());
           // this.get
            Thread.sleep(Integer.parseInt(data));
        }catch (Exception ex){

        }
        return "Executed... "+data;
    }

    @Override
    protected String getFallback() {
        return "fail.. sorry";
    }
}
