package live.hardproblem.util.entityCheck;

import live.hardproblem.dao.entity.Tag;

public class TagCheck {
    static private int tagMaxLength = 100;

    static public void insertFill(Tag tag) {
        tag.setId(null);
        tag.setStatus(true);
        updateFill(tag);
    }

    static public void updateFill(Tag tag) {
        tag.setCreatedAt(null);
        tag.setCreatedAt(null);
    }

    static public boolean insertCheck(Tag tag) {
        insertFill(tag);
        return check(tag);
    }

    static public boolean updateCheck(Tag tag) {
        updateFill(tag);
        return check(tag);
    }

    static protected boolean check(Tag tag) {
        return tag.getTag().length() <= tagMaxLength;
    }
}
