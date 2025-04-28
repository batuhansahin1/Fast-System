package financeProjects.fastSystem;

import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import financeProjects.fastSystem.core.utilities.exceptions.BusinessException;
import financeProjects.fastSystem.core.utilities.exceptions.ProblemDetails;
import financeProjects.fastSystem.core.utilities.exceptions.ValidationProblemDetails;
import io.swagger.v3.oas.annotations.Hidden;

@SpringBootApplication
@Hidden
@RestControllerAdvice 
//aşağıdaki exception handler'ın çalışabilmesi için bunu ekledik bütün controllerlar aşağıdaki exceptionlara tabi
public class FastSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastSystemApplication.class, args);
	}

//AOP Tekniği
	//uı controller'ın methodunu direkt çağırmasın diye araya aspect koyuyoruz yapılan tüm isteklere verilen cevaplar buradan geçicek
	// eğer olur da burada bir business exception fırlarsa onun hata bilgilerini dökme onun yerine bir tane nesne oluştur.Onu döndür 
	//diyeceğiz
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	//aldığımız parametre hangi hatayı hata türünü alırsak bu exceptionHandler fonksiyonu çalışsın demek
	public ProblemDetails handleBusinessException(BusinessException businessException) {
		//ProblemDetails yerine başka bir hatada validationProblemDetails bundan extend alabilir
		ProblemDetails problemDetails=new ProblemDetails(businessException.getMessage());
		return problemDetails;
	}
	//validation hatası almak için requestlerin üstünde kullandığımız anotasyonların uygulanması için  controllerda add fonk.
	//parametresinde @Valid ekliyoruz ki bunlar geçerli olsun ve kullanıcı yanlış girdiğinde validation hatası fırlatabilsin ama
	//benim spring versiyonunda hata vermiyor direkt arayüzde yanlış girdin diye uyarıyor request göndermiyor ve @Valid yazmasan
	//da bu anotasyonlar çalışıyor.Hata verdiğinde bir tane daha exceprtion handler yazıyoruz
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ValidationProblemDetails handleValidationError(MethodArgumentNotValidException methodArgumentNotValidException) {
		ValidationProblemDetails validationProblemDetails=new ValidationProblemDetails();
		validationProblemDetails.setMessage("VALIDATION EXCEPTION!!");
		validationProblemDetails.setValidationErrors(new HashMap<String, String>());
	
		for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
			
			validationProblemDetails.getValidationErrors().put(fieldError.getField(),fieldError.getDefaultMessage());
			
		}
		return validationProblemDetails; 
	}
	
	//beanleri configuration'a taşımak lazım
	//@Bean public ModelMapper  getModelMapper() {return new ModelMapper();}
	//bean tanımlama her zaman olması lazım yoksa çalışmıyor
//	@Bean public LoggingAspect myAspect() {
//		LoggingAspect loggingAspect=new LoggingAspect();
//		return loggingAspect;
//	}
}
//javadaki mapperlar sıkıntılı aşırı reflection yapıyor ve o reflection'u yaparken de 
//isim benzerliğinden dolayı belirli hatalar  olabiliyor.İçinde id olanları karıştırabiliyor bu tür işlemlerde biz araya girip set
//işlemleri yapabiliriz manuel maplemeyle modelMapper'ı aynı anda kullanabiliriz.Custom mapperlar da çok karışık genel maplemeden 
//sonra manuel maplemeler en temizi