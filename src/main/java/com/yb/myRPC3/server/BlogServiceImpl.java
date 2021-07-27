package com.yb.myRPC3.server;

import com.yb.myRPC3.common.Blog;
import com.yb.myRPC3.service.BlogService;

public class BlogServiceImpl  implements BlogService {
    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = Blog.builder().id(id).title("my blog").userId(22).build();
        System.out.println("client check : "+id+ " blog");
        return blog;
    }
}
