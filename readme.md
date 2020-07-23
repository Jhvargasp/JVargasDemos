Run HixtrixApp.java

To check the dashboard http://localhost:8082/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8082%2Fhystrix.stream

To validate echo service
http://localhost:8082/echo/100

The service will fail if time echo is grather than 200.. and the circuit will be open after 5 attemps fails.
