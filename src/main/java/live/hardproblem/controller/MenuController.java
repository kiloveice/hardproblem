package live.hardproblem.controller;

import live.hardproblem.beans.HttpResponseEntity;
import live.hardproblem.dao.entity.Menu;
import live.hardproblem.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuService menuService;

    @GetMapping("/")
    public HttpResponseEntity getAll() {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        ArrayList<Menu> menus = menuService.getAll(false);
        httpResponseEntity.setCode("200");
        httpResponseEntity.setMessage("OK");
        httpResponseEntity.setData(menus);
        return httpResponseEntity;
    }
}
