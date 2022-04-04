package live.hardproblem.service;

import live.hardproblem.dao.ExMenuMapper;
import live.hardproblem.dao.entity.Food;
import live.hardproblem.dao.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MenuService {
    @Autowired
    ExMenuMapper menuMapper;

    protected void insert_fill(Menu menu) {
        menu.setId(null);
        menu.setStatus(null);
        menu.setCreatedAt(null);
        menu.setUpdatedAt(null);
    }

    protected void update_fill(Menu menu) {
        menu.setUpdatedAt(null);
    }

    public ArrayList<Menu> getAll(boolean all) {
        return (ArrayList<Menu>) menuMapper.getAll(all);
    }

    public int insert(Menu menu){
        insert_fill(menu);
        return menuMapper.insertSelective(menu);
    }

    public int update(Menu menu){
        update_fill(menu);
        return menuMapper.updateByPrimaryKeySelective(menu);
    }
}
