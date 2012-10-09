package se.baxemyr.filehostingsite.core;

import java.util.List;

/**
 *
 * @author Marco Baxemyr
 */
public class GroupHostedFile extends AbstractHostedFile {

    private Group group;
    
    public GroupHostedFile() {
        super();
    }
    
    public Group getGroup() {
        return this.group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    
}
