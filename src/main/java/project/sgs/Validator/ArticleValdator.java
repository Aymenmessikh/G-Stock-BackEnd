package project.sgs.Validator;

import org.springframework.util.StringUtils;
import project.sgs.Dto.ArticleDto;

import java.util.ArrayList;
import java.util.List;

public class ArticleValdator {
    public static List<String> Validator(ArticleDto articleDto){
        List<String> errors=new ArrayList<>();
        if (articleDto == null){
            errors.add("Veuilez renseigne le code d'Article");
            errors.add("Veuilez renseigne la Categorie");
            errors.add("Veuilez renseigne le prixUniataireHt");
            errors.add("Veuilez renseigne le prix UnitaireTtc");
            errors.add("Veuilez renseigne le Tauxtva");
        }
        if (StringUtils.isEmpty(articleDto.getCodeArticle())){
            errors.add("Veuilez renseigne le code d'Article");
        }if (articleDto.getPrixUnitairTtc()==null){
            errors.add("Veuilez renseigne le prix UnitaireTtc");
        }if (articleDto.getTauxtva()==null) {
            errors.add("Veuilez renseigne le Tauxtva");
        }if (articleDto.getPrixUnitairHt() == null) {
            errors.add("Veuilez renseigne le prixUnitaireHt");
        }
        return errors;
    }
}
