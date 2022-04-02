package live.hardproblem.dao;

import live.hardproblem.dao.entity.MenuFood;

import java.util.List;

public interface MenuFoodMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuFood record);

    int insertSelective(MenuFood record);

    MenuFood selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuFood record);

    int updateByPrimaryKey(MenuFood record);

    List<Integer> selectFoodIdByMenuId(int id);

}