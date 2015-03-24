/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.models.UserType;
import com.photoshop.models.school.School;
import com.photoshop.models.schoolClass.SchoolClass;
import com.photoshop.models.schoolClass.SchoolClassDao;
import com.photoshop.models.student.Student;
import com.photoshop.models.student.StudentDao;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Bram
 */
@RequestMapping("/student")
@Controller
public class StudentController {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private SchoolClassDao schoolclassdao;

    //Lijst weergaven
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap map, HttpServletRequest request) {
        SchoolClass temp = schoolclassdao.getById(Integer.parseInt(request.getParameter("id")));
        map.put("students", studentDao.getStudentsBySchoolclass(temp));
        map.put("schoolclass", temp);
        return "student/list";
    }

    //Back button
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String back(ModelMap map, HttpServletRequest request) {
        SchoolClass schoolclass = schoolclassdao.getById(Integer.parseInt(request.getParameter("id")));
        School school = schoolclass.getSchool();
        return "redirect:../schoolclass/list?id=" + school.getId();
    }

    //Student aanpassen
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap map, HttpServletRequest request) {
        map.put("student", studentDao.getById(Integer.parseInt(request.getParameter("id"))));

        return "student/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String post(ModelMap map, HttpServletRequest request) {

        Student temp = studentDao.getById(Integer.parseInt(request.getParameter("id")));
        if (temp != null) {
            temp.setAddress(request.getParameter("address"));
            temp.setName(request.getParameter("name"));
            temp.setCity(request.getParameter("city"));
            temp.setZipcode(request.getParameter("zipcode"));
            temp.setUsername(request.getParameter("username"));
            studentDao.save(temp);
            return "redirect:list?id=" + temp.getSchoolclass_id();

        } else {
            System.out.println("Invalid ID");
            return "redirect:../school/list";
        }

    }

    //Toevoegen student
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        map.put("admin", "Admin panel");
        map.put("photographer", "Register of a photographer");
        return "student/add";
    }

    //Login
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap map) {
        map.put("msg", "Hello photoshop users");
        map.put("test", "testen van github account");
        return "student/login";
    }

    //Post van login
    @RequestMapping(value = "/login/checkLogin", method = RequestMethod.POST)
    public String checkLogin(@RequestParam("name") String name,
            @RequestParam("schoolcode") String code, ModelMap map, HttpServletRequest request) {
        Student student = studentDao.authenticate(name, code);
        if (student != null) {
            request.getSession().setAttribute("UserID", student.getId());
            request.getSession().setAttribute("UserName", student.getUsername());
            request.getSession().setAttribute("UserType", UserType.STUDENT);
            return "redirect:../../"; //hij zou nu ingelogd moeten zijn.
        } else {
            request.getSession().setAttribute("UserID", null);
            request.getSession().setAttribute("UserName", "");
            request.getSession().setAttribute("UserType", "");
            return "redirect:"; //teruggeleid naar de index pagina of inlogpagina
        }
    }

    @RequestMapping(value = "/accountgegevens", method = RequestMethod.GET)
    public String VraagAccountInfoOp(ModelMap map, HttpServletRequest request) {
        int userID = 0;
        UserType userType;
        String userName = "";
        Student student = null;

        try {
            userType = (UserType) request.getSession().getAttribute("UserType");
            userID = (int) request.getSession().getAttribute("UserID");
            switch (userType) {
                case STUDENT:
                    student = studentDao.getById(userID);
                    System.out.println("User ID = " + userID);
                    map.put("Type", "student");
                    map.put("UserName", student.getUsername());
                    map.put("Studentnumber", student.getStudentnr());
                    if (student.getSchoolClass() != null) {
                        map.put("Schoolcode", student.getSchoolClass().getName());
                    } else {
                        map.put("Schoolcode", "");
                    }
                    map.put("Name", student.getName());
                    map.put("Zipcode", student.getZipcode());
                    map.put("Address", student.getAddress());
                    map.put("City", student.getCity());
                    return "student/accountgegevens";
                default:
                    System.out.println("invalid type");
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "home";
        }
        return "home";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(@RequestParam("username") String username,
            @RequestParam("zipcode") String zipcode,
            @RequestParam("city") String city,
            @RequestParam("address") String address,
            ModelMap map, HttpServletRequest request) {
        System.out.println("Student is being modified");

        if (!zipcode.equals("") && !username.equals("") && !city.equals("") && !address.equals("")) {
            try {
                int userID = (int) request.getSession().getAttribute("UserID");
                Student student = this.studentDao.getById(userID);
                System.out.println("de naam van te student is " + student.getName());
                if (student != null) {
                    System.out.println("Student is being saved");
                    student.setAddress(address);
                    student.setCity(city);
                    student.setUsername(username);
                    student.setZipcode(zipcode);
                    student.save();
                } else {
                    System.out.println("student was null");
                }
                
                String newP = request.getParameter("newPassword");
                String confP = request.getParameter("confirmPassword");
                    
                if (!newP.equals("") && !confP.equals("") && newP.equals(confP)) {
                    student.setPassword(newP);
                    student.save();
                } else {
                    System.out.println("password not changed");
                }

            } catch (Exception e) {
                System.out.println("Student was null");
            }
        }

        return "home";
    }

}
