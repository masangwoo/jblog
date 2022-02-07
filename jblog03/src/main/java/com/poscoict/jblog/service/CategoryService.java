package com.poscoict.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.CategoryRepository;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.UserVo;



@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public CategoryVo insert(UserVo vo){
		return categoryRepository.insert(vo);
	}
	
	public CategoryVo insertCategory(String name, String desc, String id){
		return categoryRepository.insertCategory(name, desc, id);
	}
	
	public boolean delete(Long no) {
		
		return categoryRepository.delete(no);
	}
	
	public List<CategoryVo> findList(String id){
		return categoryRepository.findList(id);
	}

	public Long getMinCategoryNo(String id) {
		
		return categoryRepository.getMinNo(id);
	}

}
