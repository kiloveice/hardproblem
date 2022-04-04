package live.hardproblem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.hardproblem.beans.HttpResponseEntity;
import live.hardproblem.dao.entity.Tag;
import live.hardproblem.service.TagService;
import live.hardproblem.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    TagService tagService;
    //jackson json工具
    @Autowired
    ObjectMapper mapper;

    @GetMapping("/")
    public HttpResponseEntity getAll() {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        ArrayList<Tag> tags = tagService.getAll(false);
        httpResponseEntity.setCode("200");
        httpResponseEntity.setData(tags);
        httpResponseEntity.setMessage("OK");
        return httpResponseEntity;
    }

    @PostMapping("/put")
    public HttpResponseEntity put(@RequestBody Tag tag, HttpServletRequest request) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            String ip = IpUtil.getIpAddr(request);
            int flag = tagService.insert(tag);
            if (flag > 0) {
                log.warn(ip + " insert tag " + mapper.writeValueAsString(tag));
                httpResponseEntity.setCode("200");
                httpResponseEntity.setMessage("OK");
            } else {
                log.warn(ip + " failed to insert tag " + mapper.writeValueAsString(tag));
                httpResponseEntity.setCode("202");
                httpResponseEntity.setMessage("error");
            }
        } catch (Exception e) {
            log.warn(e.toString());
            httpResponseEntity.setCode("500");
        }
        return httpResponseEntity;
    }

    @PostMapping("/by_food_id")
    public HttpResponseEntity getTagByFoodId(@RequestBody Map<Object, Object> request) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        Integer foodId = (Integer) request.getOrDefault("foodId", 0);
        ArrayList<Tag> tags = tagService.getTagByFoodId(foodId, false);
        httpResponseEntity.setCode("200");
        httpResponseEntity.setData(tags);
        httpResponseEntity.setMessage("OK");
        return httpResponseEntity;
    }
}
