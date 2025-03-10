package com.liu.weibocomment.vo;

import com.liu.weibocomment.entity.TopSearch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TopSearchVo extends TopSearch {
    private Integer page;
    private Integer limit;
}
