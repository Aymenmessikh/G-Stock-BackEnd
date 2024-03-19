package project.sgs.Exeption;

import java.util.List;

public class InvalidOperationExeption extends Throwable {
    public  ErrorCode errorCode;
    public InvalidOperationExeption(String message, ErrorCode articleNotFound, List<String> articleerror){
        super(message);
    }
    public InvalidOperationExeption(String message,Throwable causse){
        super(message,causse);
    }
    public InvalidOperationExeption(String message,Throwable causse,ErrorCode errorCode){
        super(message,causse);
        this.errorCode=errorCode;
    }
    public InvalidOperationExeption(String message,ErrorCode errorCode){
        super(message);
        this.errorCode=errorCode;
    }
}
