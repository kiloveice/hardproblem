package live.hardproblem.util.entityCheck;

import live.hardproblem.dao.entity.Food;
import live.hardproblem.dao.entity.Menu;

public class MenuCheck {
    static private int descriptionMaxLength = 2000;
    static private int nameMaxLength = 100;

    static public void insertFill(Menu menu) {
        menu.setId(null);
        menu.setStatus(true);
        updateFill(menu);
    }

    static public void updateFill(Menu menu) {
        menu.setCreatedAt(null);
        menu.setUpdatedAt(null);
    }

    static protected boolean check(Menu menu) {
        return menu.getDescription().length() <= descriptionMaxLength && menu.getName().length() <= nameMaxLength;
    }

    static public boolean insertCheck(Menu menu) {
        insertFill(menu);
        return check(menu);
    }

    static public boolean updateCheck(Menu menu) {
        updateFill(menu);
        return check(menu);
    }
}
