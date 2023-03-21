package live.hardproblem.dao;

import live.hardproblem.dao.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExMenuMapper extends MenuMapper {
    public List<Menu> getAll(@Param("all") boolean all);

    public Integer getMenuCount(@Param("all") boolean all);
}
