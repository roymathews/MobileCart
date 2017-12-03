package com.sony.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sony.model.Category;

import com.sony.dao.CategoryDao;
@Controller
public class CategoryController {
	
	@Autowired (required = true)
	private CategoryDao CategoryDao;

	@RequestMapping(value="/categorysubmit",method=RequestMethod.POST)
	public ModelAndView  reg(@RequestParam("name")String name,@RequestParam("desc")String desc)
	{		
		Category c=new Category();
		c.setName(name);
		c.setDesc(desc);
		
		CategoryDao.SaveCategory(c);
		ModelAndView mv = new ModelAndView("admin");
		mv.addObject("msg","successfully added");
		mv.addObject("admin");
		return mv;
		
		
	}
	
	@RequestMapping("/listcat")
	public String listcat(Model model){
		
		model.addAttribute("listcat",this.CategoryDao.list());
		return "listcat";
	}
	
	@RequestMapping(value="/category_delete")
	public ModelAndView deleteProduct(HttpServletRequest request){
		
		Category p=CategoryDao.findById(Integer.valueOf(request.getParameter("id")));
		System.out.print(p);
		ModelAndView mv= new ModelAndView("listcat");	
		try
		{
		CategoryDao.delete(p);
		mv.addObject("msg","Category Deleted");
		}
		catch (Exception e) 
		{
			mv.addObject("msg","cannot delete");
		}
		
		List<Category> list=CategoryDao.list();
		mv.addObject("listcat",list);
		return mv;
		
	}
	@RequestMapping(value="/category_edit")
	public ModelAndView editCategory(HttpServletRequest request)
	{
		int id = Integer.parseInt(request.getParameter("id"));
		ModelAndView su = new ModelAndView("category_edit");
		List<Category> list = CategoryDao.list();
		su.addObject("category",CategoryDao.findById(id));
		su.addObject("category_edit",list);
		
		return su;
		
	}
	@RequestMapping(value="category_update",method=RequestMethod.POST)
	
	public ModelAndView ss(@RequestParam("id")int id,@RequestParam("name")String name,@RequestParam("desc")String desc)
	{	
	Category s = new Category();

	s.setId(id);
	s.setDesc(desc);
	s.setName(name);
	CategoryDao.updatecategory(s);
	ModelAndView mv = new ModelAndView("listcat");
	List<Category> list=CategoryDao.list();
	mv.addObject("listcat",list);
	mv.addObject("msg","successfully updated");
	return mv;
	
}
	
	
	
}