package project.sgs.Validator;

import project.sgs.Dto.CommandeClientDto;

import java.util.ArrayList;
import java.util.List;

public class CommandClientValidator {
    public static List<String> Validatore(CommandeClientDto commandeClientDto){
        List<String> errors=new ArrayList<>();
       /* if (commandeClientDto==null){
            return null;
        }
        if (commandeClientDto.getCode().isEmpty()){
            errors.add("Veuilez renseigne le Code de Commande");
        }
        if (commandeClientDto.getMail()==null){
            errors.add("client est null");
        }*/
        return errors;
    }
}
