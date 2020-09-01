package com.senac.assister.backend.rest.resource;

import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("api/v1")
public class TesteController {

    @GetMapping("/short")
    public String getShortUrl(@RequestBody String teste) {
        return teste;
    }
}
