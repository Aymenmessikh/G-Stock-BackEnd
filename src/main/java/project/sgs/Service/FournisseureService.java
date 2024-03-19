package project.sgs.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.sgs.Dto.FourinisseurDto;
import project.sgs.Entity.Fournisseur;
import project.sgs.Exeption.ErrorCode;
import project.sgs.Exeption.InvalideEntityExeption;
import project.sgs.Repository.FourinsseurRepository;
import project.sgs.Validator.FourinisseureValidatore;

import java.util.List;
import java.util.stream.Collectors;

import static project.sgs.Dto.FourinisseurDto.DtoToFourinisseure;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class FournisseureService {
    @Autowired
    private final FourinsseurRepository fourinsseurRepository;
    public FourinisseurDto save(FourinisseurDto fourinisseurDto){
        List<String> errors= FourinisseureValidatore.Validotor(fourinisseurDto);
        if (!errors.isEmpty()){
            log.error("Fournisseure not valide",fourinisseurDto);
            throw new InvalideEntityExeption("L'Fourinisseure n'est pas valid", ErrorCode.FOURINISSEURE_NOT_VALID,errors);
        }
        Fournisseur fournisseur=fourinsseurRepository.save(DtoToFourinisseure(fourinisseurDto));
        return fourinisseurDto;
    }
    public List<FourinisseurDto> getAllFournisseure(){
        List<Fournisseur> fournisseurs=fourinsseurRepository.findAll();
        return fournisseurs.stream().map(FourinisseurDto::DtoFromFournisseure).collect(Collectors.toList());
    }
    public FourinisseurDto getFournisseureById(Long id){
        if (id == 0){
            log.error("id note existe");
        }
        Fournisseur fournisseur=fourinsseurRepository.findFournisseurById(id);
        return FourinisseurDto.DtoFromFournisseure(fournisseur);
    }
    public void deleteFournisseure(Long id){
        if (id == 0){
            log.error("id note existe");
        }
        fourinsseurRepository.deleteById(id);
    }
}
