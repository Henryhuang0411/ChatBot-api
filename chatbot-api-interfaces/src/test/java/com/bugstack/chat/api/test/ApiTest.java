package com.bugstack.chat.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;


public class ApiTest {

    public String content_type = "application/json; charset=UTF-8";
    public String cookie = "sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2218c380f4b1d8f4-009d767bc03a6f18-26031051-1327104-18c380f4b1efa3%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThjMzgwZjRiMWQ4ZjQtMDA5ZDc2N2JjMDNhNmYxOC0yNjAzMTA1MS0xMzI3MTA0LTE4YzM4MGY0YjFlZmEzIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%2218c380f4b1d8f4-009d767bc03a6f18-26031051-1327104-18c380f4b1efa3%22%7D; zsxq_access_token=F5A27123-4E0C-3B07-A5AE-52E238FB4A91_9D3CCA85F6549B3F; abtest_env=product; zsxqsessionid=e711d71103526cc6cf80259dcc0cbe68";

    @Test
    // 获取问题
    public void query_unanswered_questions() throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/51111114551244/topics?scope=unanswered_questions&count=20");

        get.addHeader("Content-Type", content_type);
        get.addHeader("Cookie", cookie);

        CloseableHttpResponse response = httpClient.execute(get);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    //回答问题
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/question_id/answer");

        post.addHeader("Content-Type", content_type);
        post.addHeader("Cookie", cookie);

        String paramJson = "{\n" +
                " \"req_data\": {\n" +
                "   \"text\": \"wpyebuhui\\n\",\n" +
                "   \"image_ids\": [],\n" +
                "   \"silenced\": true\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity=new StringEntity(paramJson, ContentType.create("text/json","UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
}
