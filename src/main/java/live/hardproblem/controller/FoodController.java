package live.hardproblem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.hardproblem.dao.entity.Food;
import live.hardproblem.dao.entity.Tag;
import live.hardproblem.service.FoodService;
import live.hardproblem.service.ProblemService;
import live.hardproblem.service.TagService;
import live.hardproblem.util.IpUtil;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import live.hardproblem.beans.HttpResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

@Log
@RestController
public class FoodController {
    @Autowired
    FoodService foodService;
    @Autowired
    ProblemService problemService;
    @Autowired
    TagService tagService;
    //jackson json工具
    @Autowired
    ObjectMapper mapper;

    @PostMapping("/food/put")
    public HttpResponseEntity put(@RequestBody Food food, HttpServletRequest request) {
        HttpResponseEntity response = new HttpResponseEntity();
        try {
            String ip = IpUtil.getIpAddr(request);
            int flag = foodService.insert(food);
            if (flag != 0) {
                response.setCode("200");
                response.setMessage("Insert food successfully! " + flag);
                log.warning(ip + " insert food " + mapper.writeValueAsString(food));
            } else {
                response.setCode("202");
                response.setMessage("Failed insert food.");
                log.warning(ip + "failed to insert food " + mapper.writeValueAsString(food));
            }
        } catch (Exception e) {
            log.warning(e.toString());
            response.setCode("500");
        }
        return response;
    }

    @PostMapping("/food/update")
    public HttpResponseEntity update(@RequestBody Food food, HttpServletRequest request) {
        HttpResponseEntity response = new HttpResponseEntity();
        try {
            String ip = IpUtil.getIpAddr(request);
            int flag = foodService.update(food);
            if (flag != 0) {
                response.setCode("200");
                response.setMessage("Update food successfully! " + flag);
                log.warning(ip + " Update food " + mapper.writeValueAsString(food));
            } else {
                response.setCode("202");
                response.setMessage("Failed Update food.");
                log.warning(ip + "failed to insert food " + mapper.writeValueAsString(food));
            }
        } catch (Exception e) {
            log.warning(e.toString());
            response.setCode("500");
        }
        return response;
    }

    @PostMapping("/food/solve/tag")
    public HttpResponseEntity solveByTag(@RequestBody Map<Object, Object> request) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            Integer menuId = (Integer) request.getOrDefault("menu", null);
            ArrayList<Integer> tagIdList = (ArrayList<Integer>) request.get("tag");
            Food food;
            if (menuId != null) {
                food = problemService.solveWithMenuByTag(menuId, tagIdList);
            } else {
                food = problemService.solveWithoutMenuByTag(tagIdList);
            }
            httpResponseEntity.setCode("200");
            httpResponseEntity.setData(food);
            httpResponseEntity.setMessage("OK!");
        } catch (Exception e) {
            log.info(e.toString());
            httpResponseEntity.setCode("202");
            httpResponseEntity.setMessage("Error");
        }
        return httpResponseEntity;
    }

    @PostMapping("/food/get")
    public HttpResponseEntity get_all(@RequestBody Map<Object, Object> request) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int page = (int) request.getOrDefault("page", 1);
            int num = (int) request.getOrDefault("num", 10);
            ArrayList<Food> foods = foodService.selectAll(page, num);
            httpResponseEntity.setCode("200");
            httpResponseEntity.setData(foods);
            httpResponseEntity.setMessage("OK!");
        } catch (Exception e) {
            log.info(e.toString());
            httpResponseEntity.setCode("202");
            httpResponseEntity.setMessage("error");
        }
        return httpResponseEntity;
    }

    @PostMapping("/food/solve")
    public HttpResponseEntity solve(@RequestBody Map<Object, Object> request) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            Integer menuId = (Integer) request.getOrDefault("menu", null);
            Food food;
            if (menuId != null) {
                food = problemService.solveWithMenu(menuId);
            } else {
                food = problemService.solveWithoutMenu();
            }
            httpResponseEntity.setCode("200");
            httpResponseEntity.setData(food);
            httpResponseEntity.setMessage("OK!");
        } catch (Exception e) {
            log.info(e.toString());
            httpResponseEntity.setCode("202");
            httpResponseEntity.setMessage("Error");
        }
        return httpResponseEntity;
    }

    @PostMapping("/food/solve/tag/reverse")
    public HttpResponseEntity solveByTagReverse(@RequestBody Map<Object, Object> request) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            Integer menuId = (Integer) request.getOrDefault("menu", null);
            ArrayList<Integer> tagIdList = (ArrayList<Integer>) request.get("tag");
            Food food = problemService.solveWithTagReverse(menuId, tagIdList);
            httpResponseEntity.setCode("200");
            httpResponseEntity.setData(food);
            httpResponseEntity.setMessage("OK!");
        } catch (Exception e) {
            log.info(e.toString());
            httpResponseEntity.setCode("202");
            httpResponseEntity.setMessage("Error");
        }
        return httpResponseEntity;
    }

    @PostMapping("/food/addtag")
    public HttpResponseEntity addFoodTag(@RequestBody Map<Object, Object> requestData, HttpServletRequest request) {
        HttpResponseEntity response = new HttpResponseEntity();
        Integer num = 0;
        try {
            String ip = IpUtil.getIpAddr(request);
            Integer foodId = (Integer) requestData.get("foodId");
            Integer tagId = (Integer) requestData.get("tagId");
            log.warning(ip + " try add food tag foodId = " + foodId + " " + "tagId = " + mapper.writeValueAsString(tagId));
            num += tagService.addFoodTag(foodId, tagId);
            response.setCode("200");
            response.setMessage("OK");
            response.setData(num);
        } catch (Exception e) {
            log.warning(e.toString());
            response.setCode("500");
        }
        return response;
    }
}
