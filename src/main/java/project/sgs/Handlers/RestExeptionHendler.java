package project.sgs.Handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import project.sgs.Exeption.EntityNotFoundExeption;
import project.sgs.Exeption.InvalideEntityExeption;

@RestControllerAdvice
public class RestExeptionHendler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundExeption.class)
    public ResponseEntity<ErrorDto> handeleExeption(EntityNotFoundExeption exeption, WebRequest webRequest){
        final HttpStatus notFound=HttpStatus.NOT_FOUND;
        final ErrorDto errorDto= ErrorDto.builder()
                .Code(exeption.errorCode)
                .httpCode(notFound.value())
                .message(exeption.getMessage())
                .build();
        return new ResponseEntity<>(errorDto,notFound);
    }
    @ExceptionHandler(InvalideEntityExeption.class)
    public ResponseEntity<ErrorDto> hendeleExeption(InvalideEntityExeption exeption,WebRequest webRequest){
        final HttpStatus badRequest=HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto=ErrorDto.builder()
                .Code(exeption.getErrorCode())
                .httpCode(badRequest.value())
                .message(exeption.getMessage())
                .errors(exeption.errors)
                .build();
        return new ResponseEntity<>(errorDto,badRequest);
    }
}
