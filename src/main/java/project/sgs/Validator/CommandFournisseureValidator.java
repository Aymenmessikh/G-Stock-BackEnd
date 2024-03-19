package project.sgs.Validator;
import project.sgs.Dto.CommandefourinsseureDto;

import java.util.ArrayList;
import java.util.List;

public class CommandFournisseureValidator {
    public static List<String> Validatore(CommandefourinsseureDto commandefourinsseureDto){
        List<String> errors=new ArrayList<>();
        if (commandefourinsseureDto==null){
            return null;
        }
        if (commandefourinsseureDto.getCode().isEmpty()){
            errors.add("Veuilez renseigne le Code de Commande");
        }
        if (commandefourinsseureDto.getDateCommande()==null){
            errors.add("la date est null");
        }
        if (commandefourinsseureDto.getFournisseurDto()==null){
            errors.add("Fournisseure est null");
        }
        return errors;
    }
}
