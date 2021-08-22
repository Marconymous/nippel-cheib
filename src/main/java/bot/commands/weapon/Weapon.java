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

    // TODO: 8/22/2021 Add Price
    public Weapon(String name, String damage, String range, String rateOfFire, String handling, String reloadSpeed, String muzzleVelocity, String meleeDamage, String heavyMeleeDamage, Weapons enumConstant, String imageUrl) {
        this.name = name;
        this.damage = Utils.covertToCode(damage);
        this.range = Utils.covertToCode(range);
        this.rateOfFire = Utils.covertToCode(rateOfFire);
        this.handling = Utils.covertToCode(handling);
        this.reloadSpeed = Utils.covertToCode(reloadSpeed);
        this.muzzleVelocity = Utils.covertToCode(muzzleVelocity);
        this.meleeDamage = Utils.covertToCode(meleeDamage);
        this.heavyMeleeDamage = Utils.covertToCode(heavyMeleeDamage);
        this.enumConstant = enumConstant;
        this.imageUrl = imageUrl;
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
