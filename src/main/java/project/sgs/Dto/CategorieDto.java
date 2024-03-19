package project.sgs.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.sgs.Entity.Categorie;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategorieDto {
    private Long id;
    private String codeCategorie;
    private String designation;
    private List<ArticleDto> articleDtos;
    public static Categorie DtoToCategorie(CategorieDto categorieDto){
        return Categorie.builder()
                .code(categorieDto.getCodeCategorie())
                .designetion(categorieDto.getDesignation())
                .build();
    }
    public static CategorieDto DtoFromCategorie(Categorie categorie){
        return CategorieDto.builder()
                .id(categorie.getId())
                .designation(categorie.getDesignetion())
                .codeCategorie(categorie.getCode())
                .build();
    }
}
