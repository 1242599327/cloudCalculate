package com;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class DouBanGroupSpider implements PageProcessor {

    // 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    private Site site = Site.me().
            setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:56.0) Gecko/20100101 Firefox/56.0").
            setRetryTimes(3).setSleepTime(1000);

    public static void main(String[] args) {
        Spider.create(new DouBanGroupSpider()).
                addUrl("https://www.douban.com/group").
                addPipeline(new ConsolePipeline()).
                thread(5).run();
    }

    @Override
    public void process(Page page) {

    }

    @Override
    public Site getSite() {
        return site;
    }
}
