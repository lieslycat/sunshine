package com.sunshine.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.sunshine.model.ElderUser;
import com.sunshine.service.ElderUserService;
import com.sunshine.util.UUIDUtil;

/**
 * 老人用户  控制层
 * @author 云和数据-陈剑洲
 *
 */
@Controller
@RequestMapping("/elderuser")
public class ElderUserController {
	
    /**
     * 注入老人用户业务服务
     */
    @Autowired    
	private ElderUserService eus;
	
    
    /**
     * 老人用户信息根据id查询操作
     * @param id 
     * @return
     */
    @RequestMapping("/geteu")
    public ModelAndView getEu(String id){
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("id", id);
    	List<ElderUser> list = eus.listElderUser();
    	return new ModelAndView("/WEB-INF/","",list);
    	
    }
    /**
     * 老人用户信息添加操作
     * @param elderuser 老人用户对象
     * @return
     */
    @RequestMapping("/saveeu")
    public String saveElderUser(ElderUser elderuser){
    	
    	elderuser.setId(UUIDUtil.genericUUID());
    	elderuser.setElder_id("d0c63eb9-ccc7-4d76-ab39-f2ff3aa60cbd");
    	elderuser.setUser_id("f79dafec-64a6-449a-be54-201fefeec85b");
    	
    	int i=eus.saveElderUser(elderuser);
    	System.out.println("数据执行影响条数："+i);
    	return "home";
    }
    
    /**
     * 老人用户信息修改操作
     * @param elderuser 老人用户对象
     * @return
     */
    @RequestMapping("/updateeu")
    public String updateElderUser(ElderUser elderuser){
    	int i=eus.updateElderUser(elderuser);
		return "updateelderuser";
    }	
    
	/**
	 * 老人用户信息删除操作
	 * @param id 
	 */
   @RequestMapping("/removeeu")
	public void removeElderUser(String id){
		  eus.removeElderUser(id);
	   } 
 	      
    /**
     * 根据用户查询与之有关的所有老人
     * @param uid 指定用户的id
     * @return
     */
   
    @RequestMapping("/listeu")
    public ModelAndView listAllRelatedElder(String uid){
    	
    	PageHelper.startPage(1, 3, true);
    	List<Map<String, Object>> page=eus.listAllRelatedElder(uid);
    	return new ModelAndView("/WEB-INF/views/ftl/ElderUserManager/elderusermanager","elderuserPage",page);
    }
} 
