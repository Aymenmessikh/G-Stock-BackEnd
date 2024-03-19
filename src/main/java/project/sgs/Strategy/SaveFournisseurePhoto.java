package project.sgs.Strategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.sgs.Entity.Fournisseur;
import project.sgs.Repository.FourinsseurRepository;
@Service("fournisseureStrategy")
@Slf4j
@RequiredArgsConstructor
public class SaveFournisseurePhoto implements Strategy<Fournisseur> {
    @Autowired
    private final FourinsseurRepository fourinsseurRepository;
    @Override
    public  Fournisseur savePhoto(String imageName,String mail, String path){
        Fournisseur fournisseur=fourinsseurRepository.findFournisseurByMail(mail);
        fournisseur.setPhoto(path);
        fournisseur.setPhotoName(imageName);
        return fourinsseurRepository.save(fournisseur);
    }
}
