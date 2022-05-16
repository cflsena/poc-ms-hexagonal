package poc.ms.hexagonal.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import poc.ms.hexagonal.application.port.in.ExampleServicePort;
import poc.ms.hexagonal.application.port.out.ExampleRepositoryPort;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
@Slf4j
public class ExampleServicePortImpl implements ExampleServicePort {
    private final ExampleRepositoryPort exampleRepositoryPort;
}
