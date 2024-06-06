package com.aerospike.demo.springdatamultiplenamespacesexamplereactive.repositories;

import com.aerospike.demo.springdatamultiplenamespacesexamplereactive.annotations.ItemsReactiveRepository;
import com.aerospike.demo.springdatamultiplenamespacesexamplereactive.objects.Item;
import org.springframework.data.aerospike.repository.ReactiveAerospikeRepository;

@ItemsReactiveRepository
public interface AerospikeItemsReactiveRepository extends ReactiveAerospikeRepository<Item, Integer> {
}