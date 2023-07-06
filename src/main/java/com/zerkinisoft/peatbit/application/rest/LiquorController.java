package com.zerkinisoft.peatbit.application.rest;

import com.zerkinisoft.peatbit.domain.Liquor;
import com.zerkinisoft.peatbit.domain.LiquorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/liquor")
public class LiquorController {

    private final LiquorService liquorService;

    public LiquorController(LiquorService liquorService) {
        this.liquorService = liquorService;
    }

    @PostMapping
    public Integer addNewLiquor(@RequestBody @Valid Liquor liquor) {
        return liquorService.addLiquor(liquor);
    }

    @GetMapping("/{id}")
    public Liquor getLiquor(@PathVariable("id") Integer id) {
        return liquorService.getLiquor(id);
    }

}
