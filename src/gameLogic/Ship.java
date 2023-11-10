 package gameLogic;

public class Ship {
    private int xPos;
    private int yPos;

    private boolean wasHit;
    private boolean isMiss;

    public enum ShipType {
        EMPTY, DESTROYER, CRUISER, SUBMARINE, BATTLESHIP, CARRIER
    }

    private ShipType ship;
    private int shipSection;

    public enum Rotation {
        DOWN, LEFT, UP, RIGHT
    }

    private Rotation rotation;

    private boolean isEmpty;

    public Ship(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;

        this.ship = ShipType.EMPTY;
        this.shipSection = 0;
        this.rotation = Rotation.UP;

        this.isEmpty = true;

        this.wasHit = false;
        this.isMiss = false;
    }

    public Ship(int xPos, int yPos, ShipType shipType, int shipSection, Rotation rotation) {
        this.xPos = xPos;
        this.yPos = yPos;

        this.ship = shipType;
        this.shipSection = shipSection;
        this.rotation = rotation;

        this.isEmpty = false;

        this.wasHit = false;
        this.isMiss = false;
    }

    // GETTERS

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public boolean wasHit() {
        return wasHit;
    }

    public boolean wasMiss() {
        return isMiss;
    }

    public ShipType getShipType() {
        return ship;
    }

    public int getShipSection() {
        return shipSection;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    // SETTERS

    public void setShip(Ship newShip, int shipSection) {
        this.xPos = newShip.getX();
        this.yPos = newShip.getY();

        this.ship = newShip.getShipType();
        this.shipSection = shipSection;
        this.rotation = newShip.getRotation();

        this.isEmpty = false;

        this.wasHit = newShip.wasHit();
    }

    public void setShipSection(int shipSection) {
        this.shipSection = shipSection;
    }

    public void setX(int xPos) {
        this.xPos = xPos;
    }

    public void setY(int yPos) {
        this.yPos = yPos;
    }

    public void setHit() {
        this.wasHit = true;
    }

    public void setMiss() {
        this.isMiss = true;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}