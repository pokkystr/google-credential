package com.ktb.ssa.listener.pubsub.register.ssalistenerpubsubregisterservice.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateComponent {

    @Value("${rest.client.timeout}")
    private int timeout;

    public RestTemplate restTemplate() {
        return new RestTemplate(clientHttpRequestFactory());
    }

    protected ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        return clientHttpRequestFactory;
    }
}
