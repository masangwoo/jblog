package com.poscoict.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.UserVo;

@Repository
public class BlogRepository {
	
	@Autowired
	private SqlSession sqlSession;

	
	public boolean insert(UserVo vo) {
		return sqlSession.insert("blog.insert", vo) == 1;
	}
	
	public List<BlogVo> findAll(String id, long category, long post) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("category", category);
		map.put("post", post);

		List<BlogVo> list = sqlSession.selectList("blog.findAll",map);
		return list;
	}
	public List<BlogVo> findCategory(String id) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);

		List<BlogVo> list = sqlSession.selectList("blog.findCategory",map);
		return list;
	}
	
	public boolean update(BlogVo vo) {
		return sqlSession.insert("blog.update", vo) == 1;
	}

	public BlogVo select(String user_id) {
		return sqlSession.selectOne("blog.select", user_id);
	}

}
