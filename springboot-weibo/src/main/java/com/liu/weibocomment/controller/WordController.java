package com.liu.weibocomment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.weibocomment.entity.Word;
import com.liu.weibocomment.entity.Word10;
import com.liu.weibocomment.service.WordService;
import com.liu.weibocomment.vo.DataView;
import com.liu.weibocomment.vo.WordVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/word")
public class WordController {
        @Autowired
        private WordService wordService;

        @RequestMapping("/word")
        public String toWord(){
            return "word/word";
        }

        @RequestMapping("/wordData")
        public String toWordDate(Model model){
            List<Word> wordList = new ArrayList<>();
            List<Word> wordList5 = new ArrayList<>();
            wordList = wordService.listOrderByValue();
            wordList5 = wordService.listOrderByValue5();
            model.addAttribute("wordList",wordList);
            model.addAttribute("wordList5",wordList5);
            return "word/wordData";
        }

        @RequestMapping("/queryAllWord")
        @ResponseBody
        private List<Map<String,String>> queryAllWord(){
            List<Word> wordList = new ArrayList<>();
            List<Map<String,String>> mapList = new ArrayList<>();
            wordList = wordService.listOrderByValue();
            for(int i = 0; i < wordList.size(); i++){
                Word word = wordList.get(i);
                Map<String,String> map = new HashMap<>();
                map.put("name",word.getName());
                map.put("value", String.valueOf(word.getValue()));
    //            map.put("link",word.getLink());
                mapList.add(map);
            }
            return mapList;
        }

        @RequestMapping("/queryWord10")
        @ResponseBody
        public List<Word10> word10(){
            List<Word10> mapList = new ArrayList<>();
            List<Word> list = wordService.listOrderByValue10();
            for(int i = 0; i < list.size(); i++){
                Word word = new Word();
                word = list.get(i);
                Word10 word10 = new Word10();
                word10.setName(word.getName());
                word10.setValue(word.getValue());
                mapList.add(word10);
            }
            return mapList;
        }

    @RequestMapping("/listWord")
    @ResponseBody
    public DataView listWord(WordVo wordVo){
        DataView dataView = new DataView();
        IPage<Word> page = new Page<>(wordVo.getPage(),wordVo.getLimit());
        QueryWrapper<Word> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(wordVo.getName()),"name", wordVo.getName());
        wordService.page(page,queryWrapper);
        dataView.setData(page.getRecords());
        dataView.setCount(page.getTotal());
        return dataView;
    }

    @RequestMapping("/addWord")
    @ResponseBody
    public DataView addWord(Word word){
        DataView dataView = new DataView();
        boolean save = wordService.save(word);
        if(save){
            dataView.setMsg("新增微博热点话题成功");
            dataView.setCode(200);
        }else{
            dataView.setMsg("新增微博热点话题失败");
            dataView.setCode(100);
        }
        return dataView;
    }

    @RequestMapping("/updateWord")
    @ResponseBody
    public DataView updateWord(Word word){
        DataView dataView = new DataView();
        boolean update = wordService.updateById(word);
        if(update){
            dataView.setMsg("修改微博热点话题成功");
            dataView.setCode(200);
        }else{
            dataView.setMsg("修改微博热点话题失败");
            dataView.setCode(100);
        }
        return dataView;
    }

    @RequestMapping("/deleteWord/{id}")
    @ResponseBody
    public DataView deleteWord(@PathVariable("id") Integer id){
        DataView dataView = new DataView();
        boolean b = wordService.removeById(id);
        if(b){
            dataView.setMsg("删除微博热点话题成功");
            dataView.setCode(200);
        }else{
            dataView.setMsg("删除微博热点话题失败");
            dataView.setCode(100);
        }
        return dataView;
    }


    @RequestMapping("/queryWord")
    @ResponseBody
    private List<Map<String,String>> queryWord(){
        List<Word> wordList = new ArrayList<>();
        List<Map<String,String>> mapList = new ArrayList<>();
        wordList = wordService.listLimit50();
        for(int i = 0; i < wordList.size(); i++){
            Word word = wordList.get(i);
            Map<String,String> map = new HashMap<>();
            map.put("name",word.getName());
            map.put("value", String.valueOf(word.getValue()));
//            map.put("link",word.getLink());
            mapList.add(map);
        }
        return mapList;
    }

//    @RequestMapping("/queryAllWord")
//    @ResponseBody
//    private List<Map<String,String>> queryAllWord(){
//        List<Word> wordList = new ArrayList<>();
//        List<Map<String,String>> mapList = new ArrayList<>();
//        wordList = wordService.listOrderByValue();
//        for(int i = 0; i < wordList.size(); i++){
//            Word word = wordList.get(i);
//            Map<String,String> map = new HashMap<>();
//            map.put("name",word.getName());
//            map.put("value", String.valueOf(word.getValue()));
////            map.put("link",word.getLink());
//            mapList.add(map);
//        }
//        return mapList;
//    }
}
