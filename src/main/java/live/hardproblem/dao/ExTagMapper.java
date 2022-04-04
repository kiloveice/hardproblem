package live.hardproblem.dao;

import live.hardproblem.dao.entity.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExTagMapper extends TagMapper {
    List<Tag> getAll(@Param("all") boolean all);
}
