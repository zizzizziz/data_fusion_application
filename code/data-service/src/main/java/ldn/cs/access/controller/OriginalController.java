package ldn.cs.access.controller;

import ldn.cs.access.pojo.OriginalInfo;
import ldn.cs.access.service.OriginalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/data/access/original")
public class OriginalController {
    @Autowired
    private OriginalService originalService;

    @GetMapping("/query")
    public OriginalInfo query(int limit, int offset) {
        return originalService.getOriginal(limit, offset);
    }
}
