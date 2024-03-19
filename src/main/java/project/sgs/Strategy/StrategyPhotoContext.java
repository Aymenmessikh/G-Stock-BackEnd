package project.sgs.Strategy;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.sgs.Exeption.ErrorCode;
import project.sgs.Exeption.InvalidOperationExeption;
@Service
public class StrategyPhotoContext {
    private BeanFactory beanFactory;
    private Strategy strategy;
    @Autowired

    public StrategyPhotoContext(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
    public Object SavePhoto(String imageName,String context, String mail,String path_images) throws InvalidOperationExeption {
        determinContext(context);
        return strategy.savePhoto(imageName,mail,path_images);
    }

    private void determinContext(String context) throws InvalidOperationExeption {
        final String beanName=context+"Strategy";
        switch (context){
                case "article":
                    strategy=beanFactory.getBean(beanName,SaveArticlePhoto.class);
                    break;
                case "client":
                    strategy=beanFactory.getBean(beanName,SaveClientPhoto.class);
                    break;
                case "fournisseure":
                        strategy=beanFactory.getBean(beanName,SaveFournisseurePhoto.class);
                    break;
                case "utilisateure":
                    strategy=beanFactory.getBean(beanName,SaveUtilisateurePhoto.class);
                    break;
            default:throw new InvalidOperationExeption("conntext inconu", ErrorCode.Context_NOT_FOUND);
        }
    }
}
