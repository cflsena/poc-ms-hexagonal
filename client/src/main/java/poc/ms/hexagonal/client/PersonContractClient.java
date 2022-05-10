package poc.ms.hexagonal.client;

import org.springframework.cloud.openfeign.FeignClient;
import poc.ms.hexagonal.api.PersonApiContract;

@FeignClient(value = "personContractClient",
        url = "${poc.ms.hexagonal.person.url}")
public interface PersonContractClient extends PersonApiContract { }
