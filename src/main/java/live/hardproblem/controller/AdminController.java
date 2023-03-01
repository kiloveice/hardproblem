package live.hardproblem.controller;

import live.hardproblem.beans.HttpResponseEntity;
import live.hardproblem.dao.entity.Food;
import live.hardproblem.service.FoodService;
import live.hardproblem.util.HttpResponseMessage;
import live.hardproblem.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    FoodService foodService;

    @PostMapping("/delete/food")
    public HttpResponseEntity deleteFood(@RequestBody Food food, HttpServletRequest request) {
        HttpResponseEntity response = new HttpResponseEntity();
        try {
            String ip = IpUtil.getIpAddr(request);
            boolean flag = foodService.removeById(food);
            if (flag) {
                log.warn(ip + " remove food id " + food.getId());
                response.setCode(HttpResponseMessage.goodCode);
                response.setMessage(HttpResponseMessage.goodMessage);
            } else {
                log.warn(ip + " failed to remove food id " + food.getId());
                response.setCode(HttpResponseMessage.failedCode);
                response.setMessage(HttpResponseMessage.failedMessage);
            }
        } catch (Exception e) {
            log.warn(e.toString());
            response.setCode(HttpResponseMessage.errorCode);
            response.setMessage(HttpResponseMessage.errorMessage);
        }
        return response;
    }
}
