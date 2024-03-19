package project.sgs.Exeption;

import lombok.Data;

import java.util.List;
@Data
public class InvalideEntityExeption extends RuntimeException{
    private ErrorCode errorCode;
    public List<String> errors;
    public InvalideEntityExeption(String message){
        super(message);
    } public InvalideEntityExeption(String message,Throwable cousse){
        super(message,cousse);
    } public InvalideEntityExeption(String message,Throwable cousse,ErrorCode errorCode){
        super(message,cousse);
        this.errorCode=errorCode;
    } public InvalideEntityExeption(String message,ErrorCode errorCode){
        super(message);
        this.errorCode=errorCode;
    }public InvalideEntityExeption(String message,ErrorCode errorCode,List<String> errors){
        super(message);
        this.errorCode=errorCode;
        this.errors=errors;
    }
}
