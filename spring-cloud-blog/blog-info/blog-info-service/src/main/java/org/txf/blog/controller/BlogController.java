package org.txf.blog.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.txf.blog.api.BlogServiceApi;
import org.txf.blog.api.pojo.AddBlogInfoRequest;
import org.txf.blog.api.pojo.BlogInfoResponse;
import org.txf.blog.api.pojo.UpBlogRequest;
import org.txf.blog.service.BlogService;
import org.txf.common.pojo.Result;

import java.util.List;

@Slf4j
@RequestMapping("/blog")
@RestController
public class BlogController implements BlogServiceApi {
    @Autowired
    private BlogService blogService;

    @Override
    @RequestMapping("/getList")
    public Result<List<BlogInfoResponse>> getList(){
        return Result.success(blogService.getList());
    }

    @Override
    @RequestMapping("/getBlogDetail")
    public Result<BlogInfoResponse> getBlogDeatail(@NotNull Integer blogId){
        log.info("getBlogDetail, blogId: {}", blogId);
        return Result.success(blogService.getBlogDeatil(blogId));
    }


    @Override
    @RequestMapping("/add")
    public Result<Boolean> addBlog(@Validated @RequestBody AddBlogInfoRequest addBlogInfoRequest){
        log.info("addBlog 接收参数: "+ addBlogInfoRequest);
        return Result.success(blogService.addBlog(addBlogInfoRequest));
    }


    /**
     * 更新博客
     */
    @Override
    @RequestMapping("/update")
    public Result<Boolean> updateBlog(@Valid @RequestBody UpBlogRequest upBlogRequest){
        log.info("updateBlog 接收参数: "+ upBlogRequest);
        return Result.success(blogService.update(upBlogRequest));

    }

    @Override
    @RequestMapping("/delete")
    public Result<Boolean> deleteBlog(@NotNull Integer blogId){
        log.info("deleteBlog 接收参数: "+ blogId);
        return Result.success(blogService.delete(blogId));
    }
}
