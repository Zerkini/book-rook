package com.zerkinisoft.peatbit.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiquorRepository extends CrudRepository<Liquor, Integer> {

}
