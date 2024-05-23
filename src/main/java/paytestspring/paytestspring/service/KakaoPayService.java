package paytestspring.paytestspring.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
//import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import paytestspring.paytestspring.dto.KakaoPayDTO;

import org.apache.hc.core5.http.HttpEntity;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
//@Transactional
@Log
public class KakaoPayService {
    private static final String Host = "https://kapi.kakao.com";

//    @Value("#{sellentProperty['kakao.admin']}")
    private String kakaoAdminKey ="b01106db62adfb693b2b994ee78a97fa";

    private KakaoPayDTO kakaoPayDTO;

    public String kakaoPayReady() {
        // HttpClient 생성
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // POST 요청 생성
            HttpPost request = new HttpPost(new URI(Host + "/v1/payment/ready"));

            // 요청 헤더 설정
            request.setHeader("Authorization", "KakaoAK " + kakaoAdminKey);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            // 요청 바디 설정
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("cid", "TC0ONETIME"));
            params.add(new BasicNameValuePair("partner_order_id", "1001"));
            params.add(new BasicNameValuePair("partner_user_id", "goguma"));
            params.add(new BasicNameValuePair("item_name", "비둘기"));
            params.add(new BasicNameValuePair("quantity", "1"));
            params.add(new BasicNameValuePair("total_amount", "20000"));
            params.add(new BasicNameValuePair("tax_free_amount", "100"));
            params.add(new BasicNameValuePair("approval_url", "http://localhost:8080/"));
            params.add(new BasicNameValuePair("cancel_url", "http://localhost:8080/kakaoPayCancle"));
            params.add(new BasicNameValuePair("fail_url", "http://localhost:8080/kakaoPayFail"));
            request.setEntity(new UrlEncodedFormEntity(params));

            // 요청 실행
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                // 응답 처리
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    kakaoPayDTO = new ObjectMapper().readValue(((org.apache.hc.core5.http.HttpEntity) entity).getContent(), KakaoPayDTO.class);
                    log.info("" + kakaoPayDTO);
                    return kakaoPayDTO.getNext_redirect_pc_url();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return "/pay";
    }
//public String kakaoPayReady() {
//    String redirectUrl = "/pay"; // 기본값 설정
//
//    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
//        // 요청 생성
//        HttpPost request = new HttpPost(new URI(Host + "/v1/payment/ready"));
//
//        // 요청 헤더 설정
//        request.setHeader("Authorization", "KakaoAK " + kakaoAdminKey);
//        request.setHeader("Accept", "application/json");
//        request.setHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        // 요청 바디 설정
//        List<NameValuePair> params = new ArrayList<>();
//        params.add(new BasicNameValuePair("cid", "TC0ONETIME"));
//        params.add(new BasicNameValuePair("partner_order_id", "1001"));
//        params.add(new BasicNameValuePair("partner_user_id", "goguma"));
//        params.add(new BasicNameValuePair("item_name", "비둘기"));
//        params.add(new BasicNameValuePair("quantity", "1"));
//        params.add(new BasicNameValuePair("total_amount", "20000"));
//        params.add(new BasicNameValuePair("tax_free_amount", "100"));
//        params.add(new BasicNameValuePair("approval_url", "http://localhost:8080/"));
//        params.add(new BasicNameValuePair("cancel_url", "http://localhost:8080/kakaoPayCancle"));
//        params.add(new BasicNameValuePair("fail_url", "http://localhost:8080/kakaoPayFail"));
//        request.setEntity(new UrlEncodedFormEntity(params));
//
//        // 요청 실행
//        try (CloseableHttpResponse response = httpClient.execute(request)) {
//            // 응답 처리
//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                KakaoPayDTO kakaoPayDTO = new ObjectMapper().readValue(((org.apache.hc.core5.http.HttpEntity) entity).getContent(), KakaoPayDTO.class);
//                log.info("" + kakaoPayDTO);
//                redirectUrl = kakaoPayDTO.getNext_redirect_pc_url();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    } catch (IOException | URISyntaxException e) {
//        e.printStackTrace();
//    }
//    return redirectUrl;
//}
}
