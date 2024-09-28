package rough;
class GameState {
    private static GameState instance = new GameState();
    private String difficulty = "Medium";

    private GameState() {}

    public static GameState getInstance() {
        return instance;
    }

    public String getDifficulty() {
        return difficulty;
    }
}

abstract class Enemy {
    public abstract String attack();
}

class EasyEnemy extends Enemy {
    public String attack() {
        return "Easy Enemy attacks!";
    }
}

class MediumEnemy extends Enemy {
    public String attack() {
        return "Medium Enemy attacks!";
    }
}

class HardEnemy extends Enemy {
    public String attack() {
        return "Hard Enemy attacks!";
    }
}

abstract class EnemyFactory {
    public abstract Enemy createEnemy();
}

class EasyEnemyFactory extends EnemyFactory {
    public Enemy createEnemy() {
        return new EasyEnemy();
    }
}

class MediumEnemyFactory extends EnemyFactory {
    public Enemy createEnemy() {
        return new MediumEnemy();
    }
}

class HardEnemyFactory extends EnemyFactory {
    public Enemy createEnemy() {
        return new HardEnemy();
    }
}


abstract class Weapon {
    public abstract String use();
}

abstract class PowerUp {
    public abstract String apply();
}

class EasyWeapon extends Weapon {
    public String use() {
        return "Using Easy Weapon!";
    }
}

class EasyPowerUp extends PowerUp {
    public String apply() {
        return "Easy PowerUp Applied!";
    }
}

class MediumWeapon extends Weapon {
    public String use() {
        return "Using Medium Weapon!";
    }
}

class MediumPowerUp extends PowerUp {
    public String apply() {
        return "Medium PowerUp Applied!";
    }
}

abstract class GameItemFactory {
    public abstract Weapon createWeapon();
    public abstract PowerUp createPowerUp();
}

class EasyItemFactory extends GameItemFactory {
    public Weapon createWeapon() {
        return new EasyWeapon();
    }

    public PowerUp createPowerUp() {
        return new EasyPowerUp();
    }
}

class MediumItemFactory extends GameItemFactory {
    public Weapon createWeapon() {
        return new MediumWeapon();
    }

    public PowerUp createPowerUp() {
        return new MediumPowerUp();
    }
}


class Game
{
    public static void main(String[] args) {
        
        GameState gameState = GameState.getInstance();

        
        EnemyFactory enemyFactory;
        if (gameState.getDifficulty().equals("Easy")) {
            enemyFactory = new EasyEnemyFactory();
        } else if (gameState.getDifficulty().equals("Medium")) {
            enemyFactory = new MediumEnemyFactory();
        } else {
            enemyFactory = new HardEnemyFactory();
        }
        Enemy enemy = enemyFactory.createEnemy();
        System.out.println(enemy.attack());

        
        GameItemFactory itemFactory;
        if (gameState.getDifficulty().equals("Easy")) {
            itemFactory = new EasyItemFactory();
        } else {
            itemFactory = new MediumItemFactory();
        }
        Weapon weapon = itemFactory.createWeapon();
        PowerUp powerUp = itemFactory.createPowerUp();

        System.out.println(weapon.use());
        System.out.println(powerUp.apply());
    }
}
