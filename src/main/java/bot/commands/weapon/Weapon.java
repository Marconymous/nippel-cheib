package bot.commands.weapon;

import bot.utils.Utils;

public final class Weapon {
    private final String name;
    private final String damage;
    private final String range;
    private final String rateOfFire;
    private final String handling;
    private final String reloadSpeed;
    private final String muzzleVelocity;
    private final String meleeDamage;
    private final String heavyMeleeDamage;
    private final Weapons enumConstant;
    private final String imageUrl;
    private final String price;
    private final String capacity;
    private final String ammo;

    // TODO: 8/22/2021 Add Price
    public Weapon(String name, String damage, String range, String rateOfFire, String handling, String reloadSpeed, String muzzleVelocity, String meleeDamage, String heavyMeleeDamage, Weapons enumConstant, String imageUrl, String price, String capacity, String ammo) {
        this.name = name;
        this.damage = Utils.convertToCode(damage);
        this.range = Utils.convertToCode(range);
        this.rateOfFire = Utils.convertToCode(rateOfFire);
        this.handling = Utils.convertToCode(handling);
        this.reloadSpeed = Utils.convertToCode(reloadSpeed);
        this.muzzleVelocity = Utils.convertToCode(muzzleVelocity);
        this.meleeDamage = Utils.convertToCode(meleeDamage);
        this.heavyMeleeDamage = Utils.convertToCode(heavyMeleeDamage);
        this.enumConstant = enumConstant;
        this.imageUrl = imageUrl;
        this.price = Utils.convertToCode(price);
        this.capacity = Utils.convertToCode(capacity);
        this.ammo = Utils.convertToCode(ammo);
    }

    public String getName() {
        return name;
    }

    public String getDamage() {
        return damage;
    }

    public String getRange() {
        return range;
    }

    public String getRateOfFire() {
        return rateOfFire;
    }

    public String getHandling() {
        return handling;
    }

    public String getReloadSpeed() {
        return reloadSpeed;
    }

    public String getMuzzleVelocity() {
        return muzzleVelocity;
    }

    public String getMeleeDamage() {
        return meleeDamage;
    }

    public String getHeavyMeleeDamage() {
        return heavyMeleeDamage;
    }

    public Weapons getEnumConstant() {
        return enumConstant;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getAmmo() {
        return ammo;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "name='" + name + '\'' +
                ", damage='" + damage + '\'' +
                ", range='" + range + '\'' +
                ", rateOfFire='" + rateOfFire + '\'' +
                ", handling='" + handling + '\'' +
                ", reloadSpeed='" + reloadSpeed + '\'' +
                ", muzzleVelocity='" + muzzleVelocity + '\'' +
                ", meleeDamage='" + meleeDamage + '\'' +
                ", heavyMeleeDamage='" + heavyMeleeDamage + '\'' +
                ", enumConstant=" + enumConstant +
                '}';
    }
}
