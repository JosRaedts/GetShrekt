/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.misc.ImageManager;
import com.photoshop.models.IUser;
import com.photoshop.models.UserType;
import com.photoshop.models.photo.Photo;
import com.photoshop.models.photo.PhotoDao;
import com.photoshop.models.photo.PhotoJson;
import com.photoshop.models.photographer.Photographer;
import com.photoshop.models.product.ProductDao;
import com.photoshop.models.school.School;
import com.photoshop.models.school.SchoolDao;
import com.photoshop.models.schoolClass.SchoolClass;
import com.photoshop.models.schoolClass.SchoolClassDao;
import com.photoshop.models.student.Student;
import com.photoshop.models.student.StudentDao;
import net.coobird.thumbnailator.filters.Watermark;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Bram
 */
@RequestMapping("/photo")
@Controller
public class PhotoController extends AbstractController {

    @Autowired
    private PhotoDao photodao;
    
    @Autowired
    private ProductDao productdao;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private Environment env;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private SchoolClassDao schoolClassDao;

    @Autowired
    private SchoolDao schoolDao;
    
    @Autowired
    private ImageManager imageManager;

    @RequestMapping(value = {"/upload", "/upload/do_upload"}, method = RequestMethod.GET)
    public String upload() {
        if (this.authenticate(UserType.PHOTOGRAPHER)) {
            return "photo/upload";
        } else {
            return this.backendLogin();
        }
    }

    @RequestMapping(value = "/upload/do_upload", method = RequestMethod.POST)
    public @ResponseBody String do_upload(MultipartHttpServletRequest request, HttpServletRequest response) {
        if (this.authenticate(UserType.PHOTOGRAPHER)) {
            Photographer photographer = (Photographer) this.getUser();
            Iterator<String> itr = request.getFileNames();
            while (itr.hasNext()) {
                try {
                    MultipartFile mpf = request.getFile(itr.next());
                    String path = env.getProperty("uploadDir");
                    String originalFilename = mpf.getOriginalFilename();
                    String newFilename = System.currentTimeMillis() + "-" + mpf.getOriginalFilename();
                    FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(path + newFilename));
                    BufferedImage bimg = ImageIO.read(new File(path + newFilename));
                    
                    BufferedImage blowres = bimg;
                    BufferedImage bthumbnail = bimg;
                    
                    if(bimg.getHeight() > bimg.getWidth())
                    {
                        if(bimg.getHeight() > 1024)
                        {
                            int ratio = bimg.getHeight() / 1024;
                            blowres = imageManager.resize(bimg, 1024, bimg.getWidth()/ratio);
                        }
                        if(bimg.getHeight() > 100)
                        {
                            int ratio = bimg.getHeight() / 100;
                            bthumbnail = imageManager.resize(bimg, 100, bimg.getWidth()/ratio);
                        }
                    }
                    else
                    if(bimg.getWidth()> bimg.getHeight())
                    {
                        if(bimg.getWidth() > 1024)
                        {
                            int ratio = bimg.getWidth() / 1024;
                            blowres = imageManager.resize(bimg, bimg.getHeight()/ratio, 1024);
                        }
                        if(bimg.getWidth() > 100)
                        {
                            int ratio = bimg.getWidth() / 100;
                            bthumbnail = imageManager.resize(bimg, bimg.getHeight()/ratio, 100);
                        }
                    }


                    File lowdir = new File(path + "/low");
                    if (!lowdir.exists()){ lowdir.mkdirs(); }
                    File thumbdir = new File(path + "/thumb");
                    if (!thumbdir.exists()){ thumbdir.mkdirs(); }
                    
                    String lowres = path + "/low/lowres-" + newFilename;
                    String thumbnail = path + "/thumb/thumb-" + newFilename;
                    ImageIO.write(blowres, "jpg", new FileOutputStream(lowres));
                    ImageIO.write(bthumbnail, "jpg", new FileOutputStream(thumbnail));

                    Photo photo = new Photo();
                    photo.setActive(true);
                    java.util.Date utilDate = new java.util.Date();
                    photo.setDate(new Date(utilDate.getTime()));
                    photo.setHeight(bimg.getHeight());
                    photo.setWidth(bimg.getWidth());
                    photo.setHighResURL(newFilename);
                    photo.setLowResURL("lowres-" + newFilename);
                    photo.setThumbnailURL("thumb-" + newFilename);
                    photo.setPhotographerID(photographer.getId());
                    photo.save();

                    String[] file = originalFilename.split("\\.");
                    String type = file[0].split("-")[0];
                    int id = Integer.parseInt(file[0].split("-")[1]);

                    switch (type) {
                        case "student":
                            Student student = studentDao.getById(id);
                            student.addPhoto(photo);
                            break;
                        case "school":
                            School school = schoolDao.getById(id);
                            school.addPhoto(photo);
                            break;
                        case "schoolclass":
                            SchoolClass schoolClass = schoolClassDao.getById(id);
                            schoolClass.addPhoto(photo);
                            break;
                    }

                    PhotoJson photoJson = new PhotoJson(request.getRequestURI() + "/photo/view/low/" + photo.getId(),
                            request.getRequestURI() + "/photo/view/low/" + photo.getId(),
                            originalFilename,
                            "image/jpeg",
                            String.valueOf(mpf.getBytes()),
                            "",
                            "POST");
                    //JSON
                    //Files object
                    JSONObject object = new JSONObject();
                    
                    //Filelist array
                    JSONArray array = new JSONArray();
                    
                    //File object
                    JSONObject fileobject = new JSONObject();
                    fileobject.put("url", request.getRequestURI() + "/photo/view/low/" + photo.getId());
                    fileobject.put("thumbnail_url", request.getRequestURI() + "/photo/view/thumb/" + photo.getId());
                    fileobject.put("name", originalFilename);
                    fileobject.put("size", mpf.getBytes());
                    fileobject.put("delete_url", "");
                    fileobject.put("delete_type", "DELETE");
                    
                    array.put(fileobject);
                    object.put("files", array);
                    
                    return object.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "/view/{format:low|high|thumb}/{photoId:^[0-9]+$}", method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<byte[]> getPhoto(HttpServletRequest response, @PathVariable("format") String format, @PathVariable("photoId") int id) throws IOException {
        Photo photo = photodao.getById(id);
        if (photo != null) {

            IUser user = this.getUser();
            if(user != null) {
                switch (this.getUser().getType()) {
                    case PHOTOGRAPHER:
                        Photographer photographer = (Photographer) user;
                        if (photo.getPhotographerID() != photographer.getId())
                        {
                            return null;
                        }
                        break;
                    case ADMIN:
                        break;
                    case STUDENT:
                        Student student = (Student) user;
                        if(!student.doIHaveAccess(photo))
                        {
                            return null;
                        }
                        break;
                    default:
                        return null;
                }
            }
            else
            {
                return null;
            }

            String filename = "";
            switch (format) {
                case "high":
                    filename = env.getProperty("uploadDir") + photo.getHighResURL();
                    break;
                case "low":
                    filename = env.getProperty("uploadDir") + "low/" + photo.getLowResURL();
                    break;
                case "thumb":
                    filename = env.getProperty("uploadDir") + "thumb/" +photo.getThumbnailURL();
                    break;
            }

            InputStream in = new FileInputStream(filename);
            BufferedImage img = ImageIO.read(in);
            System.out.println("Watermark test1");
            if(format.equals("low"))
            {
                BufferedImage watermark = ImageIO.read(new FileInputStream(env.getProperty("uploadDir")+"watermerk.png"));
                watermark = imageManager.resize(watermark, img.getHeight(), img.getWidth());
                Watermark filter = new Watermark(Positions.CENTER, watermark, 0.2f);
                img = filter.apply(img);
                System.out.println("Watermark try");
            }
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            ImageIO.write(img, "jpg", bos);
            byte[] image = bos.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); //or what ever type it is
            headers.setContentLength(image.length);

            return new HttpEntity<byte[]>(image, headers);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap map, HttpServletRequest request) {
        if (this.authenticate(UserType.ADMIN)) {
            map.put("pictures", photodao.getList());
            return "photo/list";
        }
        return "redirect:../";
    }
    
    @RequestMapping(value = "/active/{PhotoId:^[0-9]+$}", method = RequestMethod.GET)
    public String active(ModelMap map, HttpServletRequest request,@PathVariable("PhotoId") int id) {
        
        if (id != 0)
        {
        photodao.delete(id);
        }
        
        if (this.authenticate(UserType.ADMIN)) {
        return "redirect:/photo/list";
        }
        
        if (this.authenticate(UserType.PHOTOGRAPHER)) {
            return "redirect:/photographer/photo";
        }
        return "redirect:../";
    }
    
    @RequestMapping(value = "/unlinkedlist", method = RequestMethod.GET)
    public String unlinkedList(ModelMap map, HttpServletRequest request) {
        if (this.authenticate(UserType.PHOTOGRAPHER)) {
            int userID = (int)request.getSession().getAttribute("UserID");
            map.put("pictures", photodao.getUnlinkedList(userID));
            return "photo/list";
        }
        return "redirect:../";
    }
    
    @RequestMapping(value = "/mypictures", method = RequestMethod.GET)
    public String mypictures(ModelMap map, HttpServletRequest request)
    {
        ArrayList<Photo> photos = new ArrayList<Photo>();
        if (this.authenticate(UserType.PHOTOGRAPHER,UserType.STUDENT,UserType.ADMIN)) {
            int userID = (int)request.getSession().getAttribute("UserID");
            for(Photo temp : photodao.getPhotosByStudent(userID))
            {
                if(temp.getActive() == true)
                {
                    photos.add(temp);
                }
            }
            map.put("Photo", photos);
            map.put("studentnaam", request.getSession().getAttribute("UserName").toString());
            return "photo/mypictures";
        } else {
            return "redirect:../";
        }
        
    }
    

    @RequestMapping(value = "/myschoolclasspictures", method = RequestMethod.GET)
    public String myschoolklasspictures(ModelMap map, HttpServletRequest request)
    {
        ArrayList<Photo> photos = new ArrayList<Photo>();
        if (this.authenticate(UserType.PHOTOGRAPHER,UserType.STUDENT,UserType.ADMIN))
        {
        int userID = (int)request.getSession().getAttribute("UserID");
        int schoolclassid = studentDao.getById(userID).getSchoolclass_id();
        for(Photo temp : photodao.getClassPhotosByStudentclass(schoolclassid))
            {
                if(temp.getActive() == true)
                {
                    photos.add(temp);
                }
            }
        map.put("Photo", photos);
        map.put("studentnaam", request.getSession().getAttribute("UserName").toString());
        return "photo/myschoolclasspictures";
        } else {
            return "redirect:../";
        }
    }
    
    @RequestMapping(value = "/myschoolpictures", method = RequestMethod.GET)
    public String myschoolpictures(ModelMap map, HttpServletRequest request)
    {    
        ArrayList<Photo> photos = new ArrayList<Photo>();
        if (this.authenticate(UserType.PHOTOGRAPHER,UserType.STUDENT,UserType.ADMIN))
        {
        int userID = (int)request.getSession().getAttribute("UserID");
        Student student = this.studentDao.getById(userID);
        SchoolClass schoolclass = student.getSchoolClass();
        School school = schoolclass.getSchool();
        for(Photo temp :photodao.getSchoolPhotos(school.getId()))
            {
                if(temp.getActive() == true)
                {
                    photos.add(temp);
                }
            }
        map.put("Photo", photos);
        map.put("studentnaam", request.getSession().getAttribute("UserName").toString());
        return "photo/myschoolpictures";
        } else {
            return "redirect:../";
        }
    }

    @RequestMapping(value = "/detail/{PhotoId:^[0-9]+$}", method = RequestMethod.GET)
    public String detail(ModelMap map, HttpServletRequest request, @PathVariable("PhotoId") int id) {
        if (this.authenticate(UserType.STUDENT, UserType.ADMIN, UserType.PHOTOGRAPHER))
        {
            Photo photo = photodao.getById(id);
            System.out.println("godverdommme: " + photo.getPhotographerID());
            map.put("photo", photo);
            map.put("products", productdao.getPriceList(photo.getPhotographerID()));
            return "photo/detail";
        }
        return this.frontendLogin();
    }

    @RequestMapping(value="/edit/{PhotoId:^[0-9]+$}", method = RequestMethod.GET)
    public String edit(ModelMap map, HttpServletRequest request, @PathVariable("PhotoId") int id)
    {
        if(authenticate(UserType.PHOTOGRAPHER)) {
            Photographer photographer = (Photographer) this.getUser();
            Photo photo = photodao.getById(id);
            if(photo != null) {
                if(photo.getPhotographerID() == photographer.getId()) {
                    map.put("students", studentDao.getList());
                    map.put("schoolclasses", schoolClassDao.getList());
                    map.put("schools", schoolDao.getList());
                    map.put("my_students", photo.getStudents());
                    map.put("my_schoolclasses", photo.getSchoolClasses());
                    map.put("my_schools", photo.getSchools());
                    map.put("photo", photo);
                }
            }
            return "photo/edit";
        }
        else
        {
            return backendLogin();
        }
    }

    @RequestMapping(value="/edit/{PhotoId:^[0-9]+$}", method = RequestMethod.POST)
    public String save(ModelMap map, HttpServletRequest request, @PathVariable("PhotoId") int id, @RequestParam(value = "students", required = false) int[] student_ids, @RequestParam(value = "schoolclasses", required = false) int[] schoolclass_ids, @RequestParam(value = "schools", required = false) int[] school_ids)
    {
        if(authenticate(UserType.PHOTOGRAPHER)) {
            Photographer photographer = (Photographer) this.getUser();
            Photo photo = photodao.getById(id);
            if(photo != null) {
                if(photo.getPhotographerID() == photographer.getId()) {
                    List<Student> students = new ArrayList();
                    List<SchoolClass> schoolClasses = new ArrayList();
                    List<School> schools = new ArrayList();
                    if(student_ids != null) {
                        for (int sid : student_ids) {
                            Student student = studentDao.getById(sid);
                            if (student != null) {
                                students.add(student);
                            }
                        }
                    }
                    if(schoolclass_ids != null) {
                        for (int scid : schoolclass_ids) {
                            SchoolClass schoolClass = schoolClassDao.getById(scid);
                            if (schoolClass != null) {
                                schoolClasses.add(schoolClass);
                            }
                        }
                    }
                    if(school_ids != null) {
                        for (int sid : school_ids) {
                            School school = schoolDao.getById(sid);
                            if (school != null) {
                                schools.add(school);
                            }
                        }
                    }
                    photo.saveStudents(students);
                    photo.saveSchoolClasses(schoolClasses);
                    photo.saveSchools(schools);
                }
            }
            return "redirect:../../admin";
        } else {
            return this.backendLogin();
        }
    }
}
