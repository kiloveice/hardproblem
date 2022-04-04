package live.hardproblem.service;

import live.hardproblem.dao.ExMenuMapper;
import live.hardproblem.dao.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MenuService {
    @Autowired
    ExMenuMapper menuMapper;

    public ArrayList<Menu> getAll(boolean all) {
        return (ArrayList<Menu>) menuMapper.getAll(all);
    }
}
