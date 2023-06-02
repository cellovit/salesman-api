package com.aiv.crud.salesmanapi.rest.client.impl;

import com.aiv.crud.salesmanapi.domain.dto.BranchDTO;
import com.aiv.crud.salesmanapi.exception.ClientErrorException;
import com.aiv.crud.salesmanapi.rest.client.BranchClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class BranchClientImpl implements BranchClient {

    private final RestTemplate restTemplate;

    public BranchClientImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Value("${rest.branch-api.url}")
    private String BRANCH_API_URL;

    @Override
    public BranchDTO findBranchById(long id) {
        try {
            Thread.sleep(5000);
            return restTemplate.exchange(String.format("%s%d", BRANCH_API_URL, id), HttpMethod.GET, null, BranchDTO.class)
                    .getBody();
        } catch (HttpClientErrorException | InterruptedException ex) {
            log.error(ex.getMessage());
            throw new ClientErrorException(ex.getMessage());
        }
    }

}
