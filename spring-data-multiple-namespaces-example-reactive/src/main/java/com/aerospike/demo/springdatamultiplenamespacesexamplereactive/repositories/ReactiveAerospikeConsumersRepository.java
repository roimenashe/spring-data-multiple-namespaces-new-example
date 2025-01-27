package com.aerospike.demo.springdatamultiplenamespacesexamplereactive.repositories;

import com.aerospike.demo.springdatamultiplenamespacesexamplereactive.annotations.ReactiveConsumersRepository;
import com.aerospike.demo.springdatamultiplenamespacesexamplereactive.objects.Consumer;
import org.springframework.data.aerospike.repository.ReactiveAerospikeRepository;

@ReactiveConsumersRepository
public interface ReactiveAerospikeConsumersRepository extends ReactiveAerospikeRepository<Consumer, Integer> {
}
