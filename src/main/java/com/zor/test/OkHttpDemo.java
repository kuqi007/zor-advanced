package com.zor.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by kuqi0 on 2021/7/12
 */
public class OkHttpDemo {

    public static void main(String[] args) throws InterruptedException {
        String url = "https://hrworkbench.alibaba-inc.com/offer/offerRpc/nickNameRecommend.json?name=%E6%9C%B1%E7%90%A6%E7%90%A6&sex=M&containKey=%E8%90%BD%E6%B5%B7&_tb_token_=Gg2QQdtAEv&_=1626090907319";
        Map<String, String> headers = new HashMap<>();
        headers.put("cookie", "cookieLanguageKey=0F34391E1ED476FAD959C955E2AB24B7; JSESSIONID=BD666U91-OKGRJDIO32J2U8WFSO663-Q5W75SQK-CJ7; tmp0=eNrz4A12DQ729PeL9%2FV3cfUxiKzOTLFScnIxMzMLtTTU9fd2D%2FJy8fQ3NvIyCrUIdwv2NzMz1g00DTc3DQ701nX2MlfSSS6xMjQzMjW1MDQxMbQ0NtFJTEYTyK2wMqjlii9Jii%2FJz07Ni%2BdyTzcKDEwpcXQtiwIAt74jKQ%3D%3D; xlly_s=1; offer=9B5A732A0D3D5359405E1E8CD2E52FA45FD355BF0D222AA2C23FAA0D9E652F96F7A4B493B1F6B4ED83237A898AF91A795B21C101587E0AA8CE4F64F98E687B783CA06DE615790F70905F58F949F2A9FB1074CADC6A671503785B6DBA5FCC5A2C; tfstk=cv2FBgV0w9BetAH5MvMyVnl4tM9OCxZ3qdojt5T5Xe-24BbZuk5c4UnGxSmZVqBit; l=eBryMC3Pjb6pq8dMBO5anurza77taEdX0sPzaNbMiInca1ye6UI3iNCBvLsAJdtjgT1qUyKuRYtW2d43cnQckxkbzD0WF5qTskrezexP.; isg=BHp61Aj7zhmZMkKnqz5dmv4_y6CcK_4FGH2hqoRq843Yd2qx7bhSFI7BxwOrZ3ad");
        while (true) {
            String s = OkHttpUtils.httpGet(url, headers);
            JSONObject jsonObject = JSON.parseObject(s);
            JSONArray content = jsonObject.getJSONArray("content");
            for (int i = 0; i < content.size(); i++) {
                JSONObject object = content.getJSONObject(i);
                System.out.println(object.getString("comWord"));
                if ("落海".equals(object.getString("comWord"))) {
                    System.out.println(object.toJSONString());
                    break;
                }
            }
            Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1000));
        }


    }

    public void test() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://hrworkbench.alibaba-inc.com/offer/offerRpc/nickNameRecommend.json?name=%E6%9C%B1%E7%90%A6%E7%90%A6&sex=M&containKey=%E8%90%BD%E9%A2%9C&_tb_token_=Gg2QQdtAEv&_=1626090907316")
                .method("GET", null)
                .addHeader("authority", "hrworkbench.alibaba-inc.com")
                .addHeader("pragma", "no-cache")
                .addHeader("cache-control", "no-cache")
                .addHeader("sec-ch-ua", "\" Not;A Brand\";v=\"99\", \"Google Chrome\";v=\"91\", \"Chromium\";v=\"91\"")
                .addHeader("accept", "application/json, text/javascript, */*; q=0.01")
                .addHeader("x-requested-with", "XMLHttpRequest")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                .addHeader("sec-fetch-site", "same-origin")
                .addHeader("sec-fetch-mode", "cors")
                .addHeader("sec-fetch-dest", "empty")
                .addHeader("referer", "https://hrworkbench.alibaba-inc.com/offer/nickV2.htm")
                .addHeader("accept-language", "zh-CN,zh;q=0.9")
                .addHeader("cookie", "cookieLanguageKey=0F34391E1ED476FAD959C955E2AB24B7; JSESSIONID=BD666U91-OKGRJDIO32J2U8WFSO663-Q5W75SQK-CJ7; tmp0=eNrz4A12DQ729PeL9%2FV3cfUxiKzOTLFScnIxMzMLtTTU9fd2D%2FJy8fQ3NvIyCrUIdwv2NzMz1g00DTc3DQ701nX2MlfSSS6xMjQzMjW1MDQxMbQ0NtFJTEYTyK2wMqjlii9Jii%2FJz07Ni%2BdyTzcKDEwpcXQtiwIAt74jKQ%3D%3D; lang=0F34391E1ED476FAD959C955E2AB24B7; xlly_s=1; offer=9B5A732A0D3D5359405E1E8CD2E52FA45FD355BF0D222AA2C23FAA0D9E652F96F7A4B493B1F6B4ED83237A898AF91A795B21C101587E0AA8CE4F64F98E687B783CA06DE615790F70905F58F949F2A9FB1074CADC6A671503785B6DBA5FCC5A2C; tfstk=cyfCBemntDmQzVerTwaZUx9z1dfPa43XPJthRtXE9iKjPktJXscIgnTdTTm-6KL1.; l=eBryMC3Pjb6pqPbNBO5anurza77T3pdXQsPzaNbMiInca1PJtES3iNCBvpbVSdtjgt5Df6-rFQx-eR32Sq4U-xOj64AeHJzzYvJw8e1..; isg=BJmZ9ie2HeTU9cGiNCOOl1mqqIVzJo3YH8hib7tBj0A_wtRUcHXQqdKUxIa0-iUQ; JSESSIONID=SP666IB1-C8PRVSSU1NEEOBEAWZ6S2-CZZUL0RK-4Y2; cookieLanguageKey=0F34391E1ED476FAD959C955E2AB24B7; lang=0F34391E1ED476FAD959C955E2AB24B7; tmp0=eNrz4A12DQ729PeL9%2FV3cfUxiKzOTLFSCg4wMzPzdDLUdbYICAoLDg419HN19XdydQyPMgs20nWOigr1MQjy1jWJNFLSSS6xMjQzMjOwNDY0MjYyMdBJTEYTyK2wMqiNAgAkqxwm")
                .build();
        Response response = client.newCall(request).execute();
    }

}
