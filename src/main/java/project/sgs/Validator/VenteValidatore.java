package project.sgs.Validator;
import project.sgs.Dto.VenteDto;

import java.util.ArrayList;
import java.util.List;

public class VenteValidatore {
    public static List<String> Validatore(VenteDto venteDto){
        List<String> errors=new ArrayList<>();
        if (venteDto==null){
            return null;
        }
        if (venteDto.getCode().isEmpty()){
            errors.add("Veuilez renseigne le Code de Commande");
        }
        if (venteDto.getLigneVenteDtos()==null){
            errors.add("la date est null");
        }
        if (venteDto.getDateVente()==null){
            errors.add("Fournisseure est null");
        }
        return errors;
    }
}
