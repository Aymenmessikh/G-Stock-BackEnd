package project.sgs.Validator;

import org.springframework.util.StringUtils;
import project.sgs.Dto.ClientDto;

import java.util.ArrayList;
import java.util.List;

public class CliantValidator {
    public static List<String> Validotor(ClientDto clientDto){
        List<String> errors=new ArrayList<>();
        if (clientDto==null){
            errors.add("Veuilez renseigne le Nome de client");
            errors.add("Veuilez renseigne le prenom de client");
            errors.add("Veuilez renseigne le Mail de client");
            errors.add("Veuilez renseigne l'address de client");
            errors.add("Veuilez renseigne le numero telephone de client");
            return errors;
        }
        if (StringUtils.isEmpty(clientDto.getNome())){
            errors.add("Veuilez renseigne le Nome de client");}
        if (StringUtils.isEmpty(clientDto.getPronome())){
            errors.add("Veuilez renseigne le prenom de client");}
        if (StringUtils.isEmpty(clientDto.getMail())){
            errors.add("Veuilez renseigne le Mail de client");}
        if (StringUtils.isEmpty(clientDto.getAdresse())){
            errors.add("Veuilez renseigne l'address de client");}
        if (StringUtils.isEmpty(clientDto.getNemTel())){
            errors.add("Veuilez renseigne le numero telephone de client");}
        return errors;
    }
}
