package com.poscoict.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.UserVo;

@Repository
public class CategoryRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public CategoryVo insert(UserVo vo) {
		return sqlSession.selectOne("category.insert", vo);
	}
	public CategoryVo insertCategory(String name, String desc, String id) {
		CategoryVo vo = new CategoryVo();
		vo.setCategoryName(name);
		vo.setDescription(desc);
		vo.setBlogId(id);
		return sqlSession.selectOne("category.insertCategory",vo);
	}
	
	public boolean delete(Long no) {
		
		int count = sqlSession.delete("category.delete", no);
		return 	count == 1;
	}
	
	public List<CategoryVo> findList(String id) {
		List<CategoryVo> list = sqlSession.selectList("category.findList",id);
		return list;
	}

}
