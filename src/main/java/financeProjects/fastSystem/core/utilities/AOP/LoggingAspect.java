package financeProjects.fastSystem.core.utilities.AOP;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



//bean tanımlamadan @component ile de halledebildik@Component yapmayınca aspect'i görmüyor.
//@Component  springe  aşağıdaki gibi bir class olduğunu haber veriyo @Service,@Repository gibi
@Aspect
@Component
public class LoggingAspect {

	private static final Logger LOGGER=LoggerFactory.getLogger(LoggingAspect.class);
	//execution olmadan çalışmıyor ve fonksiyon nasıl tanımlandıysa burada da öyle girmemiz lazım
	//return type ve public yazınca olmuyor önüne * koyunca oluyor
	//advice'da join pointti tanımlıyoruz bu executionlu metin
	//bu alttaki pointcut oluyor tamamen advice +pointcut expression
	@Before("execution(* financeProjects.fastSystem.webApi.PersonsController.getAll())")
	public void logBefore() {
		LOGGER.info("getAllPerson method called from aspect");
		System.out.println("getAllPerson method called from aspect");
	}

   @After("execution(* financeProjects.fastSystem.webApi.PersonsController.getAll())")
   public void logAfter() {
	   LOGGER.info("getAllPerson method called from aspect after execution");
	   System.out.println("Logging after execution");
   }

   
   @AfterThrowing("execution(* financeProjects.fastSystem.webApi.PersonsController.getAll())")
   public void logAfterThrow() {
	   LOGGER.info("error");
	   System.out.println("error");
   }
   @AfterReturning("execution(* financeProjects.fastSystem.webApi.PersonsController.getAll())")
   public void logAfterCompleted() {
	   LOGGER.info("METHOD IS CALLED ");
	   System.out.println("METHOD IS CALLED ");
   }
}
