package com.aerospike.demo.springdatamultiplenamespacesexamplereactive.configuration;

import com.aerospike.client.Host;
import com.aerospike.client.async.EventLoops;
import com.aerospike.client.async.NioEventLoops;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.reactor.IAerospikeReactorClient;
import com.aerospike.demo.springdatamultiplenamespacesexamplereactive.annotations.ReactiveItemsRepository;
import com.aerospike.demo.springdatamultiplenamespacesexamplereactive.repositories.ReactiveAerospikeItemsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.aerospike.config.AbstractReactiveAerospikeDataConfiguration;
import org.springframework.data.aerospike.config.AerospikeSettings;
import org.springframework.data.aerospike.convert.MappingAerospikeConverter;
import org.springframework.data.aerospike.core.AerospikeExceptionTranslator;
import org.springframework.data.aerospike.core.ReactiveAerospikeTemplate;
import org.springframework.data.aerospike.mapping.AerospikeMappingContext;
import org.springframework.data.aerospike.query.ReactorQueryEngine;
import org.springframework.data.aerospike.query.cache.ReactorIndexRefresher;
import org.springframework.data.aerospike.repository.config.EnableReactiveAerospikeRepositories;
import org.springframework.data.aerospike.server.version.ServerVersionSupport;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableReactiveAerospikeRepositories(basePackageClasses = ReactiveAerospikeItemsRepository.class,
        includeFilters = @ComponentScan.Filter(ReactiveItemsRepository.class),
        aerospikeTemplateRef = "reactiveAerospikeTemplateItems")
public class AerospikeItemsConfiguration extends AbstractReactiveAerospikeDataConfiguration {
    @Override
    protected Collection<Host> getHosts() {
        return Collections.singleton(new Host("localhost", 3000));
    }

    @Override
    protected String nameSpace() {
        return "items";
    }

    @Override
    protected ClientPolicy getClientPolicy() {
        ClientPolicy clientPolicy = super.getClientPolicy(); // applying default values first
        clientPolicy.user = "user";
        clientPolicy.password = "password";
        return clientPolicy;
    }

    @Override
    protected EventLoops eventLoops() {
        return new NioEventLoops(2);
    }

    @Bean(name = "reactiveAerospikeTemplateItems")
    public ReactiveAerospikeTemplate reactiveAerospikeTemplateItems(MappingAerospikeConverter mappingAerospikeConverter,
                                                                    AerospikeMappingContext aerospikeMappingContext,
                                                                    AerospikeExceptionTranslator aerospikeExceptionTranslator,
                                                                    IAerospikeReactorClient aerospikeReactorClient,
                                                                    ReactorQueryEngine reactorQueryEngine,
                                                                    ReactorIndexRefresher reactorIndexRefresher,
                                                                    ServerVersionSupport serverVersionSupport,
                                                                    AerospikeSettings settings) {
        return new ReactiveAerospikeTemplate(aerospikeReactorClient, nameSpace(),
                mappingAerospikeConverter, aerospikeMappingContext, aerospikeExceptionTranslator,
                reactorQueryEngine, reactorIndexRefresher, serverVersionSupport);
    }
}
