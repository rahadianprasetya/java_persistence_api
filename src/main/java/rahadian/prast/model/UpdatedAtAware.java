package rahadian.prast.model;

import java.time.LocalDateTime;

/**
 * Author ian
 * create 04/01/25 12.49
 */
public interface UpdatedAtAware {

    void setCreatedAt(LocalDateTime createdAt);
    void setUpdatedAt(LocalDateTime updatedAt);
}
