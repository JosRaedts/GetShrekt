package com.photoshop.controllers;

import com.photoshop.models.student.StudentDao;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author pc
 */
public class AccountInfoControllerVanBrian {
    
    ArrayList studenten;
    
    public AccountInfoControllerVanBrian()
    {
        studenten = new ArrayList();
        studenten.add("HOIHOIHOI");
    }
    
    @Autowired
    private StudentDao studentDao;
    
    //@RequestMapping(value = "/studentlist", method = RequestMethod.PUT)
    public  void FillStudentenList()
    {
        String name = "Meneer aap";
        
    }
}