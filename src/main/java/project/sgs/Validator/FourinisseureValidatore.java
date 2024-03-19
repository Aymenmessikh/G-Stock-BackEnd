package project.sgs.Validator;

import org.springframework.util.StringUtils;
import project.sgs.Dto.FourinisseurDto;

import java.util.ArrayList;
import java.util.List;

public class FourinisseureValidatore {
    public static List<String> Validotor(FourinisseurDto fourinisseurDto){
        List<String> errors=new ArrayList<>();
        if (fourinisseurDto==null){
            errors.add("Veuilez renseigne le Nome de Fourinisseur");
            errors.add("Veuilez renseigne le prenom de Fourinisseur");
            errors.add("Veuilez renseigne le Mail de Fourinisseur");
            errors.add("Veuilez renseigne l'address de Fourinisseur");
            errors.add("Veuilez renseigne le numero telephone de Fourinisseur");
            return errors;
        }
        if (StringUtils.isEmpty(fourinisseurDto.getNome())){
            errors.add("Veuilez renseigne le Nome de Fourinisseur");}
        if (StringUtils.isEmpty(fourinisseurDto.getPronome())){
            errors.add("Veuilez renseigne le prenom de Fourinisseur");}
        if (StringUtils.isEmpty(fourinisseurDto.getMail())){
            errors.add("Veuilez renseigne le Mail de Fourinisseur");}
        if (StringUtils.isEmpty(fourinisseurDto.getAdresse())){
            errors.add("Veuilez renseigne l'address de Fourinisseur");}
        if (StringUtils.isEmpty(fourinisseurDto.getNemTel())){
            errors.add("Veuilez renseigne le numero telephone de Fourinisseur");}
        return errors;
    }
}
