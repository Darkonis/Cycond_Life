package Update;


public class Monster {

  /**
   * Monster Id primary way of identifying what monster is what.
   */

  private int id;

  /**
   * Currently not being used but will determine what the monster actually is.
   */

  private int type;

  /**
   * The Longitude of the given monster
   */
  private double longitude;

  /**
   * The Latitude of the given monster
   *
   */

  private double latitude;

  public Monster() {
  }

  /**
   * Returns the monster's id
   *
   * @return the monster's id
   */
  public int getId() {
    return id;
  }

  /**
   * Returns the monster's latitude.
   *
   * @return The monster's latitude.
   */
  public double getLatitude() {
    return latitude;
  }

  /**
   * Returns the longitude for the monster
   *
   * @return The longitude for the monster
   */
  public double getLongitude() {
    return longitude;
  }

  /**
   * Returns the type of monster the current monster is.
   *
   * @return The type of monster the current monster is.
   */
  public Integer getType() {
    return type;
  }

  /**
   * Sets a monster's id to a new id.
   *
   * @param newId The new id for the given monster.
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Sets the monster's latitude to a new latitude
   *
   * @param newLat The new latitude for a given monster.
   */
  /**
   * @param latitude the latitude to set
   */
  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  /**
   * @param longitude the longitude to set
   */
  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  /**
   * @param type the type to set
   */
  public void setType(int type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Monster [id=" + id + ", type=" + type + ", longitude=" + longitude + ", latitude=" + latitude + "]";
  }
}
