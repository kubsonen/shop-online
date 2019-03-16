package pl.com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.app.entity.Image;
import pl.com.app.repository.ImageRepository;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author JNartowicz
 */
@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Transactional
    public void saveImage(Image image){
        image.setCreateDate(new Date());
        imageRepository.save(image);
    }

    @Transactional
    public Image getById(Long id){
        return imageRepository.findById(id).get();
    }

}
