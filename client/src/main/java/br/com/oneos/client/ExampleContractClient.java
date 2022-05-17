package br.com.oneos.client;

import br.com.oneos.api.ExampleApiContract;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "personContractClient",
        url = "${poc.ms.hexagonal.person.url}")
public interface ExampleContractClient extends ExampleApiContract { }
