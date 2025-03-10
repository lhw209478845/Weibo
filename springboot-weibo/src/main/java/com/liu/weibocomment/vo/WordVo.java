package com.liu.weibocomment.vo;

import com.liu.weibocomment.entity.Word;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WordVo extends Word {
    private Integer page;
    private Integer limit;
}
