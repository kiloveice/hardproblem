package live.hardproblem.service;

import com.github.pagehelper.PageHelper;
import live.hardproblem.dao.ExFoodTagMapper;
import live.hardproblem.dao.ExTagMapper;
import live.hardproblem.dao.entity.Food;
import live.hardproblem.dao.entity.FoodTag;
import live.hardproblem.dao.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {
    @Autowired
    ExTagMapper tagMapper;
    @Autowired
    ExFoodTagMapper foodTagMapper;

    protected void insert_fill(Tag tag) {
        tag.setId(null);
        tag.setStatus(null);
        tag.setCreatedAt(null);
        tag.setUpdatedAt(null);
    }

    protected void update_fill(Tag tag) {
        tag.setUpdatedAt(null);
    }

    @Cacheable(cacheNames = "tag_all")
    public ArrayList<Tag> getAll(boolean all) {
        return (ArrayList<Tag>) tagMapper.getAll(all);
    }

    @Cacheable(cacheNames = "tag_all")
    public ArrayList<Tag> getAllPage(int page, int num, boolean all) {
        PageHelper.startPage(page, num);
        return (ArrayList<Tag>) tagMapper.getAll(all);
    }

    @CacheEvict(cacheNames = "tag_all", allEntries = true)
    public int insert(Tag tag) {
        insert_fill(tag);
        return tagMapper.insertSelective(tag);
    }

    @CacheEvict(cacheNames = {"tag_all", "food_tag"}, allEntries = true)
    public int addFoodTag(Integer foodId, Integer tagId) {
        FoodTag foodTag = new FoodTag();
        foodTag.setFoodId(foodId);
        foodTag.setTagId(tagId);
        foodTag.setId(null);
        foodTag.setStatus(null);
        foodTag.setCreatedAt(null);
        foodTag.setUpdatedAt(null);
        return foodTagMapper.insertSelective(foodTag);
    }

//    涉及多表连接，废弃
//    public ArrayList<Tag> getTagByFoodId(Integer foodId, boolean all) {
//        return (ArrayList<Tag>) foodTagMapper.getTagByFoodId(foodId, all);
//    }

    @Cacheable(cacheNames = "food_tag")
    public ArrayList<Tag> getTagByFoodId(Integer foodId, boolean all) {
        List<Integer> tagIdList = foodTagMapper.getTagIdByFoodId(foodId, all);
        return (ArrayList<Tag>) tagMapper.selectInTagIdList(tagIdList, all);
    }
}
