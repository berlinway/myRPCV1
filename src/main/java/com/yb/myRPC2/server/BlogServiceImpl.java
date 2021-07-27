package com.yb.myRPC2.server;

import com.yb.myRPC2.common.Blog;
import com.yb.myRPC2.service.BlogService;

public class BlogServiceImpl  implements BlogService {
    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = Blog.builder().id(id).title("my blog").userId(22).build();
        System.out.println("client check : "+id+ " blog");
        return blog;
    }
}
