package org.txf.blog.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.txf.blog.api.pojo.AddBlogInfoRequest;
import org.txf.blog.api.pojo.BlogInfoResponse;
import org.txf.blog.api.pojo.UpBlogRequest;
import org.txf.common.pojo.Result;

import java.util.List;

@FeignClient(value = "blog-service", path = "/blog")
public interface BlogServiceApi {

    @RequestMapping("/getList")
    Result<List<BlogInfoResponse>> getList();

    @RequestMapping("/getBlogDetail")
    Result<BlogInfoResponse> getBlogDeatail(@RequestParam("blogId") Integer blogId);


    @RequestMapping("/add")
    Result<Boolean> addBlog(@RequestBody AddBlogInfoRequest addBlogInfoRequest);

    /**
     * 更新博客
     */
    @RequestMapping("/update")
    Result<Boolean> updateBlog(@RequestBody UpBlogRequest upBlogRequest);

    @RequestMapping("/delete")
    Result<Boolean> deleteBlog(@RequestParam("blogId") Integer blogId);

}
