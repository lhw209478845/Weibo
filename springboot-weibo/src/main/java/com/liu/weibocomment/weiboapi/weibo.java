package com.liu.weibocomment.weiboapi;

import com.liu.weibocomment.entity.TopSearch;
import com.liu.weibocomment.service.TopSearchService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class weibo {

    @Autowired
    private TopSearchService topSearchService;

    @Scheduled(fixedDelay = 21000000)
    public void topWeiboSearch() {
        // 1.生成httpclient，相当于该打开一个浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        // 2.创建get请求，相当于在浏览器地址栏输入 网址
        HttpGet request = new HttpGet("https://s.weibo.com/top/summary?cate=realtimehot");
        // 设置请求头，将爬虫伪装成浏览器
        request.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
        request.setHeader("cookie",
                "SUBP=0033WrSXqPxfM72-Ws9jqgMF55529P9D9WhLPPXC-lDzEwlMrH8PkwJi; SINAGLOBAL=5793462855852.432.1659345442785; SUB=_2AkMVu05ef8NxqwJRmP4TzG7ia4hyyw7EieKj57-FJRMxHRl-yj8XqlQ_tRB6PjtgsWehwvOW89UkkDT2x9eWnNH-NBuw; _s_tentry=passport.weibo.com; Apache=5534182039826.569.1659355498107; ULV=1659355498120:2:2:2:5534182039826.569.1659355498107:1659345442790");

        try {
            // 3.执行get请求，相当于在输入地址栏后敲回车键
            response = httpClient.execute(request);

            // 4.判断响应状态为200，进行处理
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 5.获取响应内容
                HttpEntity httpEntity = response.getEntity();
                String html = EntityUtils.toString(httpEntity, "utf-8");
                // System.out.println(html);

                /**
                 * 下面是Jsoup展现自我的平台
                 */
                // 6.Jsoup解析html
                Document document = Jsoup.parse(html);
                // 像js一样，通过标签获取title
                Element item = document.getElementsByTag("tbody").first();
                Elements items = item.getElementsByTag("tr");

                List<TopSearch> topSearches = new ArrayList<>();
                for(Element tmp : items) {
                    TopSearch topSearch = new TopSearch();
                    Element rankEle = tmp.getElementsByTag("td").first();
                    Elements textEle = tmp.select(".td-02").select("a");
                    Elements textNum = tmp.select(".td-02").select("span");
                    char[] num = textNum.toString().toCharArray();
                    double value = 0;
                    int pos = 0;
                    int[] values = new int[100];
                    for(int i = 0; i < num.length; i++){
                        if(num[i] >= '0' && num[i] <= '9'){
                            int t = (int) (num[i] - '0');
                            values[pos++] = t;
                        }
                    }
                    for(int i = 0; i < pos; i++){
                        value = value + Math.pow(10,pos-i-1) * values[i];
                    }

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Elements tagEle = tmp.select(".td-03");
                    if("".equals(rankEle.text()))
                        System.out.println(textEle.text() + " " + tagEle.text());
                    else {
                        String createTime = format.format(new Date());
                        Date start = format.parse(createTime);
                        topSearch.setContent(textEle.text());
                        topSearch.setContentHeat((int) value);
                        topSearch.setCreateTime(start);
                        String appear = "新";
                        if(StringUtils.isNotBlank(tagEle.text())){
                            topSearch.setAppear(tagEle.text());
                        }else{
                            topSearch.setAppear(appear);
                        }
                        topSearches.add(topSearch);

                    }
                }
                boolean save = topSearchService.saveBatch(topSearches);
            } else {
                // 如果返回状态不是200，比如404（页面不存在）等，根据情况做处理，这里略
                System.out.println("返回状态不是200");
                System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            // 6.关闭
            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpClient);
        }
    }
}
