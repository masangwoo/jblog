package com.poscoict.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.UserVo;


@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;

	public Boolean insert(UserVo vo) {
		return blogRepository.insert(vo);
	}
	
	public BlogVo Select(String user_id) {
		return blogRepository.select(user_id);
	}
	
	public Map<String, Object> getBlogList(String id, long category, long post) {
	      Map<String, Object> map = new HashMap<>();
	      List<BlogVo> list = null;
	      list =  blogRepository.findAll(id, category, post);
	      map.put("list", list);

	      return map;
	   } 
	
	public Map<String, Object> getCategoryList(String id) {
	      Map<String, Object> map = new HashMap<>();
	      List<BlogVo> list = null;
	      list =  blogRepository.findCategory(id);
	      map.put("list", list);

	      return map;
	   } 
	public Boolean update(BlogVo vo) {
		return blogRepository.update(vo);
	}
}
