package live.hardproblem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.hardproblem.beans.HttpResponseEntity;
import live.hardproblem.dao.entity.Food;
import live.hardproblem.dao.entity.Menu;
import live.hardproblem.dao.entity.MenuFood;
import live.hardproblem.dao.entity.Tag;
import live.hardproblem.service.MenuService;
import live.hardproblem.util.IpUtil;
import live.hardproblem.util.entityCheck.MenuCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuService menuService;
    //jackson json工具
    @Autowired
    ObjectMapper mapper;

    @GetMapping("/")
    public HttpResponseEntity getAll() {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        ArrayList<Menu> menus = menuService.getAll(false);
        httpResponseEntity.setCode("200");
        httpResponseEntity.setMessage("OK");
        httpResponseEntity.setData(menus);
        return httpResponseEntity;
    }

    @PostMapping("/put")
    public HttpResponseEntity put(@RequestBody Menu menu, HttpServletRequest request) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            String ip = IpUtil.getIpAddr(request);
            int flag = 0;
            if (MenuCheck.insertCheck(menu)) {
                flag = menuService.insert(menu);
            }
            if (flag > 0) {
                log.warn(ip + " insert menu " + mapper.writeValueAsString(menu));
                httpResponseEntity.setCode("200");
                httpResponseEntity.setMessage("OK");
            } else {
                log.warn(ip + " failed to insert menu " + mapper.writeValueAsString(menu));
                httpResponseEntity.setCode("202");
                httpResponseEntity.setMessage("error");
            }
        } catch (Exception e) {
            log.warn(e.toString());
            httpResponseEntity.setCode("500");
        }
        return httpResponseEntity;
    }

    @PostMapping("/update")
    public HttpResponseEntity update(@RequestBody Menu menu, HttpServletRequest request) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            String ip = IpUtil.getIpAddr(request);
            int flag = 0;
            if (MenuCheck.updateCheck(menu)) {
                flag = menuService.update(menu);
            }
            if (flag > 0) {
                log.warn(ip + " update menu " + mapper.writeValueAsString(menu));
                httpResponseEntity.setCode("200");
                httpResponseEntity.setMessage("OK");
            } else {
                log.warn(ip + " failed to update menu " + mapper.writeValueAsString(menu));
                httpResponseEntity.setCode("202");
                httpResponseEntity.setMessage("error");
            }
        } catch (Exception e) {
            log.warn(e.toString());
            httpResponseEntity.setCode("500");
        }
        return httpResponseEntity;
    }

    @PostMapping("/add_food")
    public HttpResponseEntity addFood(@RequestBody MenuFood menuFood, HttpServletRequest request) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            String ip = IpUtil.getIpAddr(request);
            int flag = menuService.addMenuFood(menuFood);
            if (flag > 0) {
                log.warn(ip + " update menu " + mapper.writeValueAsString(menuFood));
                httpResponseEntity.setCode("200");
                httpResponseEntity.setMessage("OK");
            } else {
                log.warn(ip + " failed to update menu " + mapper.writeValueAsString(menuFood));
                httpResponseEntity.setCode("202");
                httpResponseEntity.setMessage("error");
            }
        } catch (Exception e) {
            log.warn(e.toString());
            httpResponseEntity.setCode("500");
        }
        return httpResponseEntity;
    }

    @PostMapping("/by_menu_id")
    public HttpResponseEntity getFoodByMenuId(@RequestBody Map<Object, Object> request) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        Integer menuId = (Integer) request.getOrDefault("menuId", 0);
        ArrayList<Food> foods = menuService.getFoodByMenuId(menuId, false);
        httpResponseEntity.setCode("200");
        httpResponseEntity.setMessage("OK");
        httpResponseEntity.setData(foods);
        return httpResponseEntity;
    }

    @PostMapping("/get/by_menu_id")
    public HttpResponseEntity getByMenuId(@RequestBody Map<Object, Object> request) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        Integer menuId = (Integer) request.getOrDefault("menuId", 0);
        Menu menu = menuService.getByMenuId(menuId);
        httpResponseEntity.setCode("200");
        httpResponseEntity.setMessage("OK");
        httpResponseEntity.setData(menu);
        return httpResponseEntity;
    }

    @PostMapping("/delete/food")
    public HttpResponseEntity deleteFood(@RequestBody MenuFood menuFood, HttpServletRequest request) {
        HttpResponseEntity response = new HttpResponseEntity();
        try {
            String ip = IpUtil.getIpAddr(request);
            int flag = menuService.deleteFood(menuFood);
            if (flag > 0) {
                log.warn(ip + " delete food from menu, menuFood id= " + mapper.writeValueAsString(menuFood));
                response.setCode("200");
                response.setMessage("OK");
            } else {
                log.warn(ip + " failed to delete food from menu, menuFood id= " + mapper.writeValueAsString(menuFood));
                response.setCode("202");
                response.setMessage("error");
            }
        } catch (Exception e) {
            log.warn(e.toString());
            response.setCode("500");
        }
        return response;
    }
}
