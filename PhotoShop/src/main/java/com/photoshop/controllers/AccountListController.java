package com.photoshop.controllers;

import com.photoshop.models.student.StudentDao;
import java.util.ArrayList;
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
@Controller
public class AccountListController {
    
    
    @Autowired
    private StudentDao studentDao;
   
    @RequestMapping(value = "/studentlist", method = RequestMethod.GET)
    public String FillStudentenList(ModelMap map, HttpServletRequest request)
    {
        
        map.put("students", studentDao.getList());
        
        return "studentlist";
    }
    
    @RequestMapping(value = "/student/edit", method = RequestMethod.GET)
    public String editStudent(ModelMap map, HttpServletRequest request)
    {
        System.out.println(request.getParameter("id"));
        map.put("student", studentDao.getById(Integer.parseInt(request.getParameter("id"))));
        
        return "editstudent";
    }
}