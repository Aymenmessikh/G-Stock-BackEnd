package project.sgs.Validator;

import org.springframework.util.StringUtils;
import project.sgs.Dto.CategorieDto;

import java.util.ArrayList;
import java.util.List;

public class CategorieValidator {
    public static List<String>  Validate(CategorieDto categorieDto){
        List<String> errors=new ArrayList<>();
        if (categorieDto==null || StringUtils.isEmpty(categorieDto.getCodeCategorie())){
            errors.add("Veuilez renseigne le code de Categorie");
        }
        if(StringUtils.isEmpty(categorieDto.getDesignation())){
            errors.add("Veuilez renseigne le code de designation");
        }
        return errors;
    }
}
