package live.hardproblem.dao;

import live.hardproblem.dao.entity.FoodTag;

public interface FoodTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FoodTag record);

    int insertSelective(FoodTag record);

    FoodTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FoodTag record);

    int updateByPrimaryKey(FoodTag record);
}