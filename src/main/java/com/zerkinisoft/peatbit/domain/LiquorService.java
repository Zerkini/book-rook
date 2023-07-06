package com.zerkinisoft.peatbit.domain;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
public class LiquorService {

    private final LiquorRepository liquorRepository;

    public LiquorService(LiquorRepository liquorRepository) {
        this.liquorRepository = liquorRepository;
    }

    public Integer addLiquor(Liquor liquor) {
        return this.liquorRepository.save(liquor).id;
    }

    public Liquor getLiquor(Integer id) {
        return this.liquorRepository.findById(id).orElse(null);
    }

}
