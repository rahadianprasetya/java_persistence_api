package rahadian.prast.listener;

import rahadian.prast.model.UpdatedAtAware;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * Author ian
 * create 04/01/25 12.51
 */
public class UpdatedAtListener {


    @PrePersist
    @PreUpdate
    public void lastUpdatedAt(UpdatedAtAware object) {
        object.setCreatedAt(LocalDateTime.now());
        object.setUpdatedAt(LocalDateTime.now());
    }


}
