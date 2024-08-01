public class Robot {
  private String id;
  private String type;
  private String name;
  private String position;
  private float speed;
  private float batteryLevel;
  private float cpu;
  private float memory;
  private boolean active;
  private Fournisseur supplier;



  // Getters
  public String getId() {return id;}

  public String getType() {return type;}

  public String getName() {return name;}

  public String getPosition() {return position;}

  public float getSpeed() {return speed;}

  public float getBatteryLevel() {return batteryLevel;}

  public float getCpu() {return cpu;}

  public float getMemory() {return memory;}

  public boolean isActive() {return active;}

  public Fournisseur getSupplier() {return supplier;}

  // Setters
  public void setId(String id) {this.id = id;}

  public void setType(String type) {this.type = type;}

  public void setName(String name) {this.name = name;}

  public void setPosition(String position) {this.position = position;}

  public void setSpeed(float speed) {this.speed = speed;}

  public void setBatteryLevel(float batteryLevel) {this.batteryLevel = batteryLevel;}

  public void setCpu(float cpu) {this.cpu = cpu;}

  public void setMemory(float memory) {this.memory = memory;}

  public void setActive(boolean active) {this.active = active;}

  public void setSupplier(Fournisseur supplier) {this.supplier = supplier;}
}
