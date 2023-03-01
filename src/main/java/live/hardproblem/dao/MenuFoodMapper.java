package live.hardproblem.dao;

import live.hardproblem.dao.entity.MenuFood;

public interface MenuFoodMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuFood record);

    int insertSelective(MenuFood record);

    MenuFood selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuFood record);

    int updateByPrimaryKey(MenuFood record);
}