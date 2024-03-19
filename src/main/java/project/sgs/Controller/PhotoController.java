package project.sgs.Controller;

import com.flickr4java.flickr.FlickrException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.sgs.Exeption.InvalidOperationExeption;
import project.sgs.Strategy.StrategyPhotoContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequiredArgsConstructor
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private StrategyPhotoContext strategyPhotoContext;
    /*@PostMapping("/save/{id}/{title}/{context}")
    public ResponseEntity<Object> save(@PathVariable Long id, @PathVariable String title, @PathVariable String context, @RequestPart MultipartFile photo) throws IOException, FlickrException, InvalidOperationExeption {
        Object objet=strategyPhotoContext.SavePhoto(context,id,photo.getInputStream(),title);
        return new ResponseEntity<>(objet, HttpStatus.CREATED);
    }*/
    @PostMapping("/save/{mail}/{context}")
    public ResponseEntity<Object> uploadImages(@RequestParam("image") MultipartFile image,@PathVariable String mail, @PathVariable String context) throws IOException, FlickrException, InvalidOperationExeption {
        System.out.println(image.getOriginalFilename());
        System.out.println(image.getName());
        System.out.println(image.getSize());
        System.out.println(image.getContentType());
        String path_Directory = "C:\\Users\\USER\\Avance\\SGS\\src\\main\\resources\\static\\images";
        Files.copy(image.getInputStream(), Paths.get(path_Directory + File.separator +image.getOriginalFilename()),
                StandardCopyOption.REPLACE_EXISTING);

        strategyPhotoContext.SavePhoto(image.getOriginalFilename(),context, mail,path_Directory);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/get/{name}")
    public ResponseEntity<byte[]> getImage(@PathVariable String name) {
        String path_Directory = "C:\\Users\\USER\\Avance\\SGS\\src\\main\\resources\\static\\images";
        File imageFile = new File(path_Directory, name);

        if (imageFile.exists() && imageFile.isFile()) {
            try {
                byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(imageBytes);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
