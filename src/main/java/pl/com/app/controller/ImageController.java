package pl.com.app.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.app.entity.Image;
import pl.com.app.service.ImageService;
import pl.com.app.service.ProductService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author JNartowicz
 */
@Controller
@RequestMapping(value = ImageController.IMAGE_PATH)
public class ImageController {

    public static final String IMAGE_PATH = "/image";
    public static final String IMAGE_PRODUCT_PATH = "/product";
    public static final String IMAGE_PRODUCT_THUMB_PATH = "/productThumb";

    @Autowired
    private ImageService imageService;

    @Autowired
    private ProductService productService;

    @GetMapping(IMAGE_PRODUCT_PATH + "/{imgId}")
    public void getProductImage(HttpServletResponse response,
                                  @PathVariable("imgId") String imgId){

        FileInputStream file = null;
        try{
            //Get image
            Image image = imageService.getById(Long.valueOf(imgId));
            file = new FileInputStream(new File(image.getServerPath() + image.getName()));
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            IOUtils.copy(file, response.getOutputStream());
        } catch (Throwable t){
//            t.printStackTrace();
        } finally {
            if(file != null){
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @GetMapping(IMAGE_PRODUCT_THUMB_PATH + "/{productCode}")
    public void getProductThumb(HttpServletResponse response,
                                @PathVariable("productCode") String productCode){

        FileInputStream file = null;
        try{
            //Get image
            Image image = imageService.getById(productService.getProductThumbImgId(productCode));
            file = new FileInputStream(new File(image.getServerPath() + image.getName()));
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            IOUtils.copy(file, response.getOutputStream());
        } catch (Throwable t){
//            t.printStackTrace();
        } finally {
            if(file != null){
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
