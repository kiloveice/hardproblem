package live.hardproblem.util.entityCheck;

import live.hardproblem.dao.entity.Food;

public class FoodCheck {
    static private int descriptionMaxLength = 2000;
    static private int nameMaxLength = 100;

    static public void insertFill(Food food) {
        food.setId(null);
        food.setStatus(true);
        updateFill(food);
    }

    static public void updateFill(Food food) {
        food.setCreatedAt(null);
        food.setUpdatedAt(null);
    }

    static protected boolean check(Food food) {
        return food.getDescription().length() <= descriptionMaxLength && food.getName().length() <= nameMaxLength;
    }

    static public boolean insertCheck(Food food) {
        insertFill(food);
        return check(food);
    }

    static public boolean updateCheck(Food food) {
        updateFill(food);
        return check(food);
    }
}
