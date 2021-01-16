package com.dormitory.app;

import com.dormitory.app.database.Business;
import com.dormitory.app.database.InsertData;
import com.dormitory.app.database.ServiceDatabase;
import com.dormitory.app.helpful.MarketNewsCreator;
import com.dormitory.app.helpful.PictureMarket;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Controller
public class MarketStuff {
    @RequestMapping(value = "/market/items/s/{id}")
    public String marketItemSet(@PathVariable("id") String id){

        return "saveImageHtml";
    }


    @RequestMapping(value = "/market/items/g/{id}")
    public ModelAndView marketItemGet(@PathVariable("id") String id,
                                      Model model,
                                      HttpSession session) throws IOException {
        String folder = "/src/main/resources/static/photos/";
        ArrayList<PictureMarket> allPicturesFromDBById = Business.getAllPicturesFromDBById(Integer.parseInt(id));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test2");

        for (PictureMarket pictureMarket : allPicturesFromDBById) {
            Path absolutePath = Paths.get(".").toAbsolutePath();
            Path path = Paths.get( absolutePath + folder + pictureMarket.getIdPicture() + ".jpg");
            pictureMarket.setPath(absolutePath + folder + pictureMarket.getIdPicture() + ".jpg");
            pictureMarket.setName(String.valueOf(pictureMarket.getIdPicture()));
            System.out.println("Путь - " + path);
            Files.write(path, pictureMarket.getBytes());
        }

        modelAndView.addObject("pictures", allPicturesFromDBById);
        return modelAndView;
    }

    @RequestMapping(value = "/market/items/get")
    public String helpToMarketToGetItem(Model model){
        return "test2";
    }



    @RequestMapping(value = "/uploadImage")
    public String uploadImage(@RequestParam("imageFile") MultipartFile multipartFile, Model model, HttpSession session) throws IOException {
        if (session.getAttribute("login") == null){
            model.addAttribute("error", "Вы не вошли в систему");
            return "error";
        }
        String folder = "src/main/resources/static/";
        byte[] bytes = multipartFile.getBytes();
        // Path path = Paths.get(folder + multipartFile.getOriginalFilename());
        // Files.write(path, bytes);

        // Метод определяет id картинки внутри
        String pictureId = InsertData.putPictureToDBRelatedToNewById(-10, bytes);
        //
        // Business.getAllPicturesFromDBById(Integer.parseInt(pictureId)).stream().forEach(System.out::println);


        folder = "/src/main/resources/static/photos/";
        // picture id !!!
        PictureMarket pictureMarket = Business.getPictureFromDBByIdPicture(Integer.parseInt(pictureId));

        Path absolutePath = Paths.get(".").toAbsolutePath();
        Path path = Paths.get( absolutePath + folder + pictureId + ".jpg");
        pictureMarket.setPath(absolutePath + folder + pictureId + ".jpg");
        pictureMarket.setName(pictureId);
        System.out.println("Путь - " + path);
        Files.write(path, pictureMarket.getBytes());


//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("test2");
//        modelAndView.addObject("pictures", allPicturesFromDBById);

        return "redirect:/addMarketNews?pictureId=" + pictureId;
    }


    @RequestMapping(value = "/market/items/show/{id}", method = RequestMethod.GET)
    public String getImageAsResponseEntity(Model model, HttpSession session, @PathVariable("id") int id) throws IOException {
        // model.addAttribute("imagePath1", "KK4Oq2VMtGQ.jpg");
        model.addAttribute("pictureId", id);

        ArrayList<PictureMarket> allPicturesFromDBById = Business.getAllPicturesFromDBById(id);
        model.addAttribute("allImages", allPicturesFromDBById);

        return "test2";
    }

    @RequestMapping(value = "/service")
    public String service(HttpSession session){
        if (session.getAttribute("login") == null){
            return "redirect:/";
        }
        return "service";
    }


//    public String getSomething(HttpSession session, HttpServletResponse response) throws IOException {
//        ServletContext servletContext = session.getServletContext();
//        InputStream in = servletContext.getResourceAsStream("/WEB-INF/images/image-example.jpg");
//        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
//        IOUtils.copy(in, response.getOutputStream());
//        return "";
//    }
//    @RequestMapping("/testME")
//    public ResponseEntity<byte[]> getImageAsResponseEntity(HttpSession session) throws IOException {
//        HttpHeaders headers = new HttpHeaders();
//        ServletContext servletContext = session.getServletContext();
//
//
//
//        InputStream in = servletContext.getResourceAsStream("/resources/static/photos/1610148928.jpg");
//        byte[] media = Business.getPictureFromDBByIdPicture(1610148928).getBytes();
//        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
//
//        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
//        return responseEntity;
//    }

    @GetMapping(value = "/classpath")
    public ResponseEntity<byte[]> fromClasspathAsResEntity() throws IOException {

        ClassPathResource imageFile = new ClassPathResource("/WEB-INF/images/0.jpg");

        byte[] imageBytes = Business.getPictureFromDBByIdPicture(1610148928).getBytes();

        InputStream in = new ByteArrayInputStream(imageBytes);

        BufferedImage buf = ImageIO.read(in);
        int height = buf.getHeight();
        int width = buf.getWidth();

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    @GetMapping(value = "/classpath/new")
    public String newBean(Model model) throws IOException {

        ClassPathResource imageFile = new ClassPathResource("/WEB-INF/images/0.jpg");

        byte[] imageBytes = Business.getPictureFromDBByIdPicture(1610148928).getBytes();

        InputStream in = new ByteArrayInputStream(imageBytes);

        BufferedImage buf = ImageIO.read(in);
        int height = buf.getHeight();
        int width = buf.getWidth();

        model.addAttribute("width", width);
        model.addAttribute("height", height);
        model.addAttribute("bytes", imageBytes);
        return "test4";
    }
}
