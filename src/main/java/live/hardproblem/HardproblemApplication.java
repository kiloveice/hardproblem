package live.hardproblem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("live.hardproblem.dao")
@SpringBootApplication
public class HardproblemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HardproblemApplication.class, args);
	}

}
