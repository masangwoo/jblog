package com.poscoict.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.security.Auth;
import com.poscoict.jblog.security.AuthUser;
import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.service.CategoryService;
import com.poscoict.jblog.service.FileUploadService;
import com.poscoict.jblog.service.PostService;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;
import com.poscoict.jblog.vo.UserVo;

@Controller
@RequestMapping("/blog/{id:(?!assets).*}")
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	@Autowired
	private FileUploadService fileUploadService;

	@Autowired
	private ServletContext context;
	
	/*@RequestMapping("/{id}")
	   public String main(@PathVariable("id") String id, Model model) {
	    Map<String, Object> map = new HashMap<>();
	    map = blogService.getBlogList(id,1,1);
	    List<CategoryVo> catList = categoryService.findList(id);
	    PostVo postVo = postService.view(1);
		model.addAttribute("blog", map);
		model.addAttribute("catList",catList);
		model.addAttribute("postVo", postVo);
		System.out.println(map);
		System.out.println(catList);
		System.out.println(postVo);
	      return "/blog/blog-main";
	   }
	
	@RequestMapping("/{id}/{categoryNo}")
	   public String main(@PathVariable("id") String id, 
			   			  @PathVariable("categoryNo") long categoryNo, Model model) {
	    Map<String, Object> map = new HashMap<>();
	    map = blogService.getBlogList(id,categoryNo,1);
	    List<CategoryVo> catList = categoryService.findList(id);
	    PostVo postVo = postService.view(1);
		model.addAttribute("blog", map);
		model.addAttribute("catList",catList);
		model.addAttribute("postVo", postVo);
	      return "/blog/blog-main";
	   }
	
	@RequestMapping("/{id}/{categoryNo}/{postNo}")
	   public String main(@PathVariable("id") String id, 
			   			  @PathVariable("categoryNo") long categoryNo, 
			   			@PathVariable("postNo") long postNo, Model model) {
	    Map<String, Object> map = new HashMap<>();
	    map = blogService.getBlogList(id,categoryNo,postNo);
	    List<CategoryVo> catList = categoryService.findList(id);
	    PostVo postVo = postService.view(postNo);
		model.addAttribute("blog", map);
		model.addAttribute("catList",catList);
		model.addAttribute("postVo", postVo);
	      return "/blog/blog-main";
	   }*/
	
	@RequestMapping({"","/{pathNo1}","/{pathNo1}/{pathNo2}"})
	   public String main(@PathVariable("id") String id,
			   @PathVariable("categoryNo") Optional<Long> pathNo1, 
	   			@PathVariable("postNo") Optional<Long> pathNo2, Model model) {
		Long categoryNo=0L;
		Long postNo=0L;
		
		if(pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		}else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		
	    Map<String, Object> map = new HashMap<>();
	    map = blogService.getBlogList(id,categoryNo,postNo);
	    List<CategoryVo> catList = categoryService.findList(id);
	    PostVo postVo = postService.view(postNo);
		model.addAttribute("blog", map);
		model.addAttribute("catList",catList);
		model.addAttribute("postVo", postVo);
	      return "/blog/blog-main";
	   }
	
	@Auth
	@RequestMapping("/admin")
	   public String adminBasic(@PathVariable("id") String id, 
			   @AuthUser UserVo authUser,
			   			 Model model) {
		
	      return "/blog/blog-admin-basic";
	   }
	
	@Auth
	@RequestMapping(value="/admin/update", method = RequestMethod.POST)
	public String mainUpdate(@RequestParam(value="upload-file") MultipartFile multipartFile,
			@PathVariable("id") String id,
			BlogVo vo,
			@AuthUser UserVo uservo) {
		String url = fileUploadService.restore(multipartFile);	
		vo.setLogo(url);
		vo.setUserId(uservo.getId());
		blogService.update(vo);
		context.setAttribute("blogvo", vo);
		return "redirect:/blog/"+id+"/admin";
	}
	
	
	@Auth
	@RequestMapping("/admin/category")
	   public String adminCategory(@PathVariable("id") String id, 
			   			 Model model) {
		Map<String, Object> map = new HashMap<>();
	    map = blogService.getCategoryList(id);
		model.addAttribute("blog", map);
	      return "blog/blog-admin-category";
	   }
	
	@Auth
	@RequestMapping(value="/category/add", method=RequestMethod.POST)
	   public String addCategory(@PathVariable("id") String id, String name, String desc) {
		
		categoryService.insertCategory(name, desc, id);
		
	      return "redirect:/blog/"+ id + "/admin/category";
	   }
	
	@Auth
	@RequestMapping(value="/category/delete/{categoryno}",method=RequestMethod.GET)
	public String category(@PathVariable(value = "categoryno") Long categoryno, @AuthUser UserVo uservo) {
		categoryService.delete(categoryno);
		return "redirect:/blog/"+ uservo.getId() + "/admin/category";
	}
	
	@Auth
	@RequestMapping("/admin/write")
	   public String adminWrite(@PathVariable("id") String id, 
			   			 Model model) {
		Map<String, Object> map = new HashMap<>();
	    map = blogService.getCategoryList(id);
		model.addAttribute("blog", map);
	      return "blog/blog-admin-write";
	   }
	
	@Auth
	@RequestMapping(value="/admin/write",method=RequestMethod.POST)
	public String write(@AuthUser UserVo uservo, PostVo postvo, CategoryVo categoryvo) {
		postService.write(postvo);
		return "redirect:/blog/"+ uservo.getId();
	}
}
