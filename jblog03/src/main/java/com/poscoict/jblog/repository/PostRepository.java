package com.poscoict.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

@Repository
public class PostRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public PostVo view(long no) {
		PostVo vo = new PostVo();
		vo.setNo(no);
		return sqlSession.selectOne("post.view",vo);
	}
	
	public boolean write(PostVo vo) {
		int count = sqlSession.update("post.write",vo);
		return count==1;
	}

}
