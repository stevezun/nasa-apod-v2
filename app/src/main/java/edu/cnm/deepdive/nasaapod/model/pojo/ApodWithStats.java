package edu.cnm.deepdive.nasaapod.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import edu.cnm.deepdive.nasaapod.model.entity.Apod;
import java.util.Date;

public class ApodWithStats {

  @Embedded
  private Apod apod;

  private Date lastAccess;

  private int accessCount;

  public Apod getApod() {
    return apod;
  }

  public void setApod(Apod apod) {
    this.apod = apod;
  }

  public Date getLastAccess() {
    return lastAccess;
  }

  public void setLastAccess(Date lastAccess) {
    this.lastAccess = lastAccess;
  }

  public int getAccessCount() {
    return accessCount;
  }

  public void setAccessCount(int accessCount) {
    this.accessCount = accessCount;
  }

  @NonNull
  @Override
  public String toString() {
    return String.format( "%s (%s); last accessed = %s; access count = %d", apod.getTitle(), apod.getMediaType(), lastAccess, accessCount );
  }
}
