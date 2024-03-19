package project.sgs.Strategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.sgs.Entity.Utlisateure;
import project.sgs.Repository.UtilisateureRepository;
@Service("utilisateureStrategy")
@Slf4j
@RequiredArgsConstructor
public class SaveUtilisateurePhoto implements Strategy<Utlisateure>{
    @Autowired
    private final UtilisateureRepository utilisateureRepository;
    @Override
    public Utlisateure savePhoto(String imageName,String mail, String path) {
       Utlisateure utlisateure=utilisateureRepository.findUtlisateureByEmail(mail);
        utlisateure.setPhoto(path);
        utlisateure.setPhotoName(imageName);
        return utilisateureRepository.save(utlisateure);
    }
}
