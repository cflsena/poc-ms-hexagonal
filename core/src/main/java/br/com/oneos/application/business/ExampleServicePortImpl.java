package br.com.oneos.application.business;

import br.com.oneos.application.port.db.out.ExampleRepositoryPort;
import br.com.oneos.application.port.service.in.ExampleServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
@Slf4j
public class ExampleServicePortImpl implements ExampleServicePort {
    private final ExampleRepositoryPort exampleRepositoryPort;
}
