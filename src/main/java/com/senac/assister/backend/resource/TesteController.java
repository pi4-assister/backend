package com.senac.assister.backend.resource;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("api/v1")
public class TesteController {

    @GetMapping("/short")
    public String getShortUrl(@RequestBody String teste) {

        return null;
    }
}
