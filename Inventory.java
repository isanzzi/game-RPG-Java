import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private Weapon equippedWeapon;
    private Magic equippedMagic;
    private final List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
        System.out.println(item.getName() + " ditambahkan ke inventory.");
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean hasItems() {
        return !items.isEmpty();
    }

    public Item getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            Item removed = items.remove(index);
            System.out.println(removed.getName() + " dihapus dari inventory.");
        }
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void equipWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
    }

    public Magic getEquippedMagic() {
        return equippedMagic;
    }

    public void equipMagic(Magic magic) {
        this.equippedMagic = magic;
    }
}
