package com.poscoict.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.PostRepository;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;



@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	
	public PostVo view(long no){
		return postRepository.view(no);
	}
	
	public boolean write(PostVo vo) {
		return postRepository.write(vo);
	}

	public Long getMaxPostNo(long no) {
		
		return postRepository.getMaxNo(no);
	}


}
