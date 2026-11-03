package org.txf.blog.service;


import org.txf.blog.api.pojo.AddBlogInfoRequest;
import org.txf.blog.api.pojo.BlogInfoResponse;
import org.txf.blog.api.pojo.UpBlogRequest;

import java.util.List;

public interface BlogService {
    List<BlogInfoResponse> getList();

    BlogInfoResponse getBlogDeatil(Integer blogId);

    Boolean addBlog(AddBlogInfoRequest addBlogInfoRequest);

    Boolean update(UpBlogRequest upBlogRequest);

    Boolean delete(Integer blogId);
}
