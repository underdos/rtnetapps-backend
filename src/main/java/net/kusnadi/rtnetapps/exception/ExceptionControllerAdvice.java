package net.kusnadi.rtnetapps.exception;

        import net.kusnadi.rtnetapps.entity.ServiceResponse;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.ControllerAdvice;
        import org.springframework.web.bind.annotation.ExceptionHandler;
        import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by root on 18/09/17.
 */
@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ServiceResponse> exceptionHandler(Exception ex) {
        ServiceResponse sr = new ServiceResponse();
        sr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        sr.setMessage("Please contact your administrator");
        return new ResponseEntity<ServiceResponse>(sr, HttpStatus.OK);
    }
}
