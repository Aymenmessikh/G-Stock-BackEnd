package project.sgs.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.sgs.Dto.CategorieDto;
import project.sgs.Entity.Categorie;
import project.sgs.Exeption.ErrorCode;
import project.sgs.Exeption.InvalideEntityExeption;
import project.sgs.Repository.CategorieRepository;
import project.sgs.Validator.CategorieValidator;

import java.util.List;
import java.util.stream.Collectors;

import static project.sgs.Dto.CategorieDto.DtoFromCategorie;
import static project.sgs.Dto.CategorieDto.DtoToCategorie;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CategorieService {
    @Autowired
    private final CategorieRepository categorieRepository;
    public CategorieDto save(CategorieDto categorieDto){
       if (categorieRepository.findCategorieByCode(categorieDto.getCodeCategorie())!=null){
           throw new InvalideEntityExeption("L'Categorie n'est pas valid", ErrorCode.CATEGORIE_NOT_VALID);
       }
           List<String> errors= CategorieValidator.Validate(categorieDto);
           if (!errors.isEmpty()){
               log.error("Categorie not valide",categorieDto);
               throw new InvalideEntityExeption("L'Categorie n'est pas valid", ErrorCode.CATEGORIE_NOT_VALID,errors);
           }
           Categorie categorie=categorieRepository.save(DtoToCategorie(categorieDto));
      return categorieDto;
    }
    public List<CategorieDto> getAllCategorie(){
        List<Categorie> categories=categorieRepository.findAll();
        return categories.stream().map(CategorieDto::DtoFromCategorie).collect(Collectors.toList());
    }
    public CategorieDto getCategorieById(Long id){
        if (id == 0){
            log.error("id note existe");
        }
        Categorie categorie=categorieRepository.findCategorieById(id);
        return DtoFromCategorie(categorie);
    }
    public void deleteCategorie(Long id){
        if (id == 0){
            log.error("id note existe");
        }
        categorieRepository.deleteById(id);
    }
    public CategorieDto geybyCode(String code){
        Categorie categorie=categorieRepository.findCategorieByCode(code);
        return DtoFromCategorie(categorie);
    }
}
