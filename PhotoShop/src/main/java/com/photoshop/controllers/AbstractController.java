/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.models.UserType;
import com.photoshop.models.IUser;
import com.photoshop.models.admin.AdminDao;
import com.photoshop.models.photographer.PhotographerDao;
import com.photoshop.models.student.StudentDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author Bram
 */
public class AbstractController {
    
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private PhotographerDao photographerDao;
    @Autowired
    private AdminDao adminDao;
    
    public AbstractController()
    {
        System.out.println("testAbstract");
    }
        
    public IUser getUser()
    {
        IUser user = null;
        HttpSession session = request.getSession();   
        try {
            if(user == null)
            {
                int userID = (int)session.getAttribute("UserID");
                if(userID != 0)
                {
                    UserType type = (UserType)session.getAttribute("UserType");
                    switch(type)
                    {
                        case STUDENT:
                            user = studentDao.getById(userID);
                            break;
                        case PHOTOGRAPHER:
                            user = photographerDao.getById(userID);
                            break;
                        case ADMIN:
                            user = adminDao.getById(userID);
                            break;
                        default:
                            break;
                    }
                }
                System.out.println("re-get user");
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex+"exception JONGUH");
        }
        return user;
    }
    
    public boolean authenticate(UserType userType1)
    {
        IUser user = this.getUser();
        if(user != null)
        {
            if(this.getUser().getType() == userType1)
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean authenticate(UserType userType1, UserType userType2)
    {
        IUser user = this.getUser();
        if(user != null)
        {
            if(this.getUser().getType() == userType1 || this.getUser().getType() == userType2)
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean authenticate(UserType userType1, UserType userType2, UserType userType3)
    {
        IUser user = this.getUser();
        if(user != null)
        {
            if(this.getUser().getType() == userType1 || this.getUser().getType() == userType2 || this.getUser().getType() == userType3)
            {
                return true;
            }
        }
        return false;
    }
    
}
