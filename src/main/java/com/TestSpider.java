package com;

import com.sun.javafx.fxml.builder.URLBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class TestSpider {

    private static Logger logger=Logger.getLogger(TestSpider.class.getName());

    public static void main(String[] args) throws Exception {
        //1.打开浏览器
        HttpClient httpClient = HttpClients.createDefault();
        //2.打开网址
        URIBuilder uriBuilder = new URIBuilder("https://search.jd.com/Search");
        uriBuilder.addParameter("keyword","电脑");
        uriBuilder.addParameter("enc","utf-8");
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        //HttpGet httpGet = new HttpGet("https://diannao.jd.com/");
        logger.info("发起请求："+httpGet.toString());
        //3.发起请求
        CloseableHttpResponse response =null;
        try {
            response = (CloseableHttpResponse) httpClient.execute(httpGet);
            //4.解析相应获取数据
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = response.getEntity();
                String content = EntityUtils.toString(httpEntity,"utf-8");
                System.out.println(content);
                System.out.println(content.length());
                String regrex="";
                Pattern pattern= Pattern.compile(regrex);
            }
        }catch (Exception e)
        {
            logger.warning(e.toString());
        }finally {
            try {
                response.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            try {
                httpClient.getConnectionManager().shutdown();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }
}
