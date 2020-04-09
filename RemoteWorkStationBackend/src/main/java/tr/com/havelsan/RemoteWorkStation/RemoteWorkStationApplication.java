package tr.com.havelsan.RemoteWorkStation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RemoteWorkStationApplication {

	public static void main(String[] args){
		try{
			SpringApplication.run(RemoteWorkStationApplication.class, args);
		}catch (Exception ex){
			ex.getStackTrace();
		}

	}
}
