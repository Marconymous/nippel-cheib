package bot.commands.weapon

import bot.utils.Utils.convertToCode

class Weapon(
    val name: String,
    damage: String?,
    range: String?,
    rateOfFire: String?,
    handling: String?,
    reloadSpeed: String?,
    muzzleVelocity: String?,
    meleeDamage: String?,
    heavyMeleeDamage: String?,
    enumConstant: Weapons,
    imageUrl: String,
    price: String?,
    capacity: String?,
    ammo: String?,
    slotCategory: String
) {
    val damage: String
    val range: String
    val rateOfFire: String
    val handling: String
    val reloadSpeed: String
    val muzzleVelocity: String
    val meleeDamage: String
    val heavyMeleeDamage: String
    val enumConstant: Weapons
    val imageUrl: String
    val price: String
    val capacity: String
    val ammo: String
    private val slotCategory: String
    override fun toString(): String {
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
                '}'
    }

    // TODO: 8/22/2021 Add Price
    init {
        this.damage = convertToCode(damage) ?: ""
        this.range = convertToCode(range) ?: ""
        this.rateOfFire = convertToCode(rateOfFire) ?: ""
        this.handling = convertToCode(handling) ?: ""
        this.reloadSpeed = convertToCode(reloadSpeed) ?: ""
        this.muzzleVelocity = convertToCode(muzzleVelocity) ?: ""
        this.meleeDamage = convertToCode(meleeDamage) ?: ""
        this.heavyMeleeDamage = convertToCode(heavyMeleeDamage) ?: ""
        this.enumConstant = enumConstant
        this.imageUrl = imageUrl
        this.price = convertToCode(price) ?: ""
        this.capacity = convertToCode(capacity) ?: ""
        this.ammo = convertToCode(ammo) ?: ""
        this.slotCategory = slotCategory
    }
}