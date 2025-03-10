package com.liu.weibocomment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.weibocomment.entity.QueryTopsearch;
import com.liu.weibocomment.entity.TopSearch;
import com.liu.weibocomment.service.TopSearchService;
import com.liu.weibocomment.vo.DataView;
import com.liu.weibocomment.vo.TopSearchVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/topsearch")
public class TopSearchController {
    @Autowired
    private TopSearchService topSearchService;

    @RequestMapping("/toIndex")
    public String toIndex(Model model){
        List<TopSearch> topSearcheList = topSearchService.listTopSearchsLimit10();
        model.addAttribute("topSearchList",topSearcheList);
        return "topsearch/index";
    }

    @RequestMapping("/toTopSearch")
    public String toTopSearch(){
        return "topsearch/topsearch";
    }

    @RequestMapping("/query")
    @ResponseBody
    public List<QueryTopsearch> queryTopsearch(){
        List<TopSearch> topSearchList = topSearchService.listTopSearchsLimit30();
        List<QueryTopsearch> topsearches = new ArrayList<>();
        for(int i = 0; i < topSearchList.size(); i++){
            TopSearch topSearch = topSearchList.get(i);
            QueryTopsearch queryTopsearch = new QueryTopsearch();
            queryTopsearch.setName(topSearch.getContent());
            queryTopsearch.setValue(String.valueOf(topSearch.getContentHeat()));
            topsearches.add(queryTopsearch);
        }
        return topsearches;
    }

    @RequestMapping("/listTopSearch")
    @ResponseBody
    public DataView listTopSearch(TopSearchVo topSearchVo) throws ParseException {
        DataView dataView = new DataView();
        IPage<TopSearch> page = new Page<>(topSearchVo.getPage(),topSearchVo.getLimit());
        QueryWrapper<TopSearch> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(topSearchVo.getContent()),"content",topSearchVo.getContent());
        queryWrapper.like(StringUtils.isNotBlank(topSearchVo.getAppear()),"appear",topSearchVo.getAppear());
        topSearchService.page(page,queryWrapper);
        dataView.setCount(page.getTotal());
        dataView.setData(page.getRecords());
        return dataView;
    }

    @RequestMapping("/addTopSearch")
    @ResponseBody
    public DataView addTopSearch(TopSearch topSearch){
        DataView dataView = new DataView();
        boolean save = topSearchService.save(topSearch);
        if(save){
            dataView.setCode(200);
            dataView.setMsg("新增微博成功！");
        }else{
            dataView.setCode(100);
            dataView.setMsg("新增微博失败");
        }
        return dataView;
    }

    @RequestMapping("/updateSearch")
    @ResponseBody
    public DataView updateTopSearch(TopSearch topSearch){
        DataView dataView = new DataView();
        boolean update = topSearchService.updateById(topSearch);
        if(update){
            dataView.setCode(200);
            dataView.setMsg("修改微博成功");
        }else{
            dataView.setCode(100);
            dataView.setMsg("修改微博失败");
        }
        return dataView;
    }

    @RequestMapping("/deleteTopSearch/{id}")
    @ResponseBody
    public DataView deleteTopSearch(@PathVariable("id") Integer id){
        DataView dataView = new DataView();
        boolean delete = topSearchService.removeById(id);
        if(delete){
            dataView.setCode(200);
            dataView.setMsg("删除微博成功");
        }else{
            dataView.setCode(100);
            dataView.setMsg("删除微博失败");
        }
        return dataView;
    }
}
