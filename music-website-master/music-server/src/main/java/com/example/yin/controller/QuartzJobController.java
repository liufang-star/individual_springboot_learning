package com.example.yin.controller;

import com.example.yin.service.impl.ListSongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 刘芳
 * @date 2022-05-19
 * @description 定时任务控制层
 */
@RestController
@Controller
public class QuartzJobController {

    @Autowired
    private ListSongServiceImpl listSongService;

    //    返回歌单里指定歌单ID的歌曲
    @RequestMapping(value = "/quartzJob/list", method = RequestMethod.GET)
    public Object quartzJobList(HttpServletRequest req){
        String songListId = req.getParameter("songListId");
        return listSongService.quartzJobList(Integer.parseInt(songListId));
    }

}
