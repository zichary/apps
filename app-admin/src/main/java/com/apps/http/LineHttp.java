package com.apps.http;

import com.apps.properties.LineProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @Author: SimonYang
 * @Date: 2019-03-06
 * @Version: 1.0.0
 */
@Slf4j
@Component
public class LineHttp {

    @Autowired
    private LineProperties lineProperties;

    public void post(String text) throws IOException {
        if (!lineProperties.isEnabled()) {
            return;
        }
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        HashMap<String, Object> object = new HashMap<>(16);
        object.put("to", lineProperties.getTo());
        List<HashMap<String, String>> messages = new ArrayList<>();
        HashMap<String, String> message = new HashMap<>(16);
        message.put("type", "text");
        message.put("text", text);
        messages.add(message);
        object.put("messages", messages);
        MediaType json = MediaType.parse("application/json; charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        RequestBody body = RequestBody.create(json, mapper.writeValueAsString(object));
        Request request = new Request.Builder()
                .header("Authorization", String.format("Bearer %s", lineProperties.getChannelToken()))
                .url("https://api.line.me/v2/bot/message/push")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                log.warn("發送失敗{}", Optional.of(response.body().toString()));
                //throw new IOException("Unexpected code " + response);

            }
        }
    }
}
