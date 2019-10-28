package com;

import org.jsoup.helper.StringUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class CsdnBlogProcessor implements PageProcessor {

    private static String username = "yixiao1874";// 设置csdn用户名
    private static int size = 0;// 共抓取到的文章数量

    // 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {

        if (!page.getUrl().regex("https://blog.csdn.net/" + username + "/article/details/\\d+").match()) {
            String number="0";
            //获取当前页码
            List<String> scripts=page.getHtml().regex("<script.*?>[\\\\s.]*?</script>").all();
            for (String script : scripts){
                if(script.indexOf("currentPage")!=-1){
                    int index=script.indexOf(";");
                    number = script.substring(index-1,index);
                };
            }
            //String links=page.getHtml().links().toString();
            //匹配当前页码+1的页码也就是下一页，加入爬取列表中
            String targetUrl = "http://blog.csdn.net/" + username + "/article/list/" + (Integer.parseInt(number) + 1)+"?";
            List<Request> requests=page.getTargetRequests();
            boolean is=true;
            for (Request request : requests){
                if (request.getUrl().equals(targetUrl)){
                    is = false;
                }
            }
            if(is){
                page.addTargetRequest(targetUrl);
                List<String> detailUrls = page.getHtml().xpath("//div[@class='article-item-box csdn-tracking-statistics']/h4/a/@href").all();
                for (String list : detailUrls) {
                    System.out.println(list);
                }
                page.addTargetRequests(detailUrls);
            }

        } else {
            size++;// 文章数量加1
            CsdnBlog csdnBlog = new CsdnBlog();
            String path = page.getUrl().get();
            //String content =page.getHtml().get();
            int id = Integer.parseInt(path.substring(path.lastIndexOf("/") + 1));
            String title = page.getHtml().xpath("//h1[@class='title-article']/text()").get();
            String date = page.getHtml().xpath("//div[@class='article-info-box']//span[@class='time']/text()").get();
            String copyright = page.getHtml().xpath("//div[@class='article-title-box']/span/text()").get();
            int view = Integer.parseInt(page.getHtml().xpath("//div[@class='article-info-box']//span[@class='read-count']/text()").get());
            csdnBlog.id(id).title(title).date(date).copyright(copyright).view(view);
            page.putField(String.valueOf(id),csdnBlog.toString());
            System.out.println(csdnBlog);
        }
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        // 从用户博客首页开始抓，开启5个线程，启动爬虫
        Spider.create(new CsdnBlogProcessor())
                .addUrl("http://blog.csdn.net/" + username)
                .addPipeline(new JsonFilePipeline("E:\\webmagic"))
                .thread(5).run();
        System.out.println("文章总数为" + size);
    }
}
