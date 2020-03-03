package saxion.n481246.myzoo;

public class ShopItem {
    private String itemName;
    private int price;
    private int imageId;
    private int bgColor;

    public ShopItem() {
    }

    public ShopItem(String itemName, int price, int imageId, int bgColor) {
        this.itemName = itemName;
        this.price = price;
        this.imageId = imageId;
        this.bgColor = bgColor;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }
}
