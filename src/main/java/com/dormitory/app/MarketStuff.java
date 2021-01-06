package com.dormitory.app;

import com.dormitory.app.database.Business;
import com.dormitory.app.database.InsertData;
import com.dormitory.app.helpful.MarketNewsCreator;
import com.dormitory.app.helpful.PictureMarket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
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
    @ResponseBody
    public String uploadImage(@RequestParam("imageFile") MultipartFile multipartFile) throws IOException {
        String folder = "src/main/resources/static/";
        byte[] bytes = multipartFile.getBytes();
        // Path path = Paths.get(folder + multipartFile.getOriginalFilename());
        // Files.write(path, bytes);

        InsertData.putPictureToDBRelatedToNewById(1, bytes);
        Business.getAllPicturesFromDBById(1).stream().forEach(System.out::println);
        return "Успех!";
    }


    @RequestMapping(value = "/market/items/show/{id}", method = RequestMethod.GET)
    public String getImageAsResponseEntity(Model model, HttpSession session) throws IOException {
        // model.addAttribute("imagePath1", "KK4Oq2VMtGQ.jpg");

        return "test2";
    }

    @RequestMapping(value = "/service")
    @ResponseBody
    public String service(){
        return "";
    }
}
