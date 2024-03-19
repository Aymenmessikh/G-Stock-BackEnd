package project.sgs.Exeption;

import java.util.List;

public class EntityNotFoundExeption extends  RuntimeException{
    //si en recherche a un article par son code par exemple et ne trouve pas l'article genere cette exeption
    public  ErrorCode errorCode;
    public EntityNotFoundExeption(String message, ErrorCode articleNotFound, List<String> articleerror){
        super(message);
    }public EntityNotFoundExeption(String message,Throwable causse){
        super(message,causse);
    }public EntityNotFoundExeption(String message,Throwable causse,ErrorCode errorCode){
        super(message,causse);
        this.errorCode=errorCode;
    }
    public EntityNotFoundExeption(String message,ErrorCode errorCode){
        super(message);
        this.errorCode=errorCode;
    }


}