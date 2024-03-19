package project.sgs.Validator;
import io.micrometer.common.util.StringUtils;
import project.sgs.Dto.UtlisateureDto;

import java.util.ArrayList;
import java.util.List;

public class UtilisateureValidator {
    public static List<String> Validator(UtlisateureDto utlisateureDto){
        List<String> errors=new ArrayList<>();
       if (utlisateureDto==null){
            errors.add("Veuilez renseigne votre Email");
            errors.add("Veuilez renseigne votre Password");
            errors.add("Veuilez renseigne le Roles");
            errors.add("Veuilez renseigne votre UserName");
            return errors;
        }
        if (StringUtils.isEmpty(utlisateureDto.getEmail())) {
            errors.add("Veuilez renseigne votre Email");
        }
        if (StringUtils.isEmpty(utlisateureDto.getPassword())) {
            errors.add("Veuilez renseigne votre Password");
        }  if (StringUtils.isEmpty(utlisateureDto.getNom())) {
            errors.add("Veuilez renseigne votre Nome");
        }
        return errors;
    }
}
