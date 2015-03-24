/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.models.UserType;
import com.photoshop.models.school.School;
import com.photoshop.models.school.SchoolDao;
import com.photoshop.models.schoolClass.SchoolClass;
import com.photoshop.models.schoolClass.SchoolClassDao;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author pc
 */
@RequestMapping("/schoolclass")
@Controller
public class SchoolClassController extends AbstractController {

    @Autowired
    private SchoolDao schooldao;

    @Autowired
    private SchoolClassDao schoolclassDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap map, HttpServletRequest request) {
        if (authenticate(UserType.ADMIN)) {
            try {
                School temp = schooldao.getById(Integer.parseInt(request.getParameter("id")));
                map.put("schoolclasses", temp.getSchoolClasses());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return "redirect:../school/list";
            }

            return "schoolclass/list";
        }
        return "redirect:../";
    }

    //Student aanpassen
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap map, HttpServletRequest request) {
        if (authenticate(UserType.ADMIN)) {
            map.put("schoolclass", schoolclassDao.getById(Integer.parseInt(request.getParameter("id"))));

            return "schoolclass/edit";
        }
        return "redirect:../";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String post(ModelMap map, HttpServletRequest request) {
        if (authenticate(UserType.ADMIN)) {
            SchoolClass temp = schoolclassDao.getById(Integer.parseInt(request.getParameter("id")));
            int schoolid = 0;
            if (temp != null) {
                temp.setName(request.getParameter("name"));
                schoolid = temp.getId();
                schoolclassDao.save(temp);

            } else {
                System.out.println("Invalid ID");
            }

            return "redirect:list?id=" + schoolid;
        }
        return "redirect:../";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addpage(ModelMap map, HttpServletRequest request) {
        if (authenticate(UserType.ADMIN)) {
            map.put("schools", schooldao.getList());
            return "schoolclass/add";
        }
        return "redirect:../";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(ModelMap map, HttpServletRequest request) {
        if (authenticate(UserType.ADMIN)) {
            SchoolClass temp = new SchoolClass();
            try {
                temp.setName(request.getParameter("name"));
                temp.setSchool_id(Integer.parseInt(request.getParameter("school")));
                schoolclassDao.save(temp);

            } catch(Exception ex) {
                System.out.println("Invalid ID");
                System.out.println(ex.getMessage());
            }

            return "redirect:../school/list";
        }
        return "redirect:../";
    }
}