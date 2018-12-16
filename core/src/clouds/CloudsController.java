package clouds;

// Sonntag, 6. Mai 2018  endlich ja!

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import helpers.GameInfo;

public class CloudsController {
    private World world;
    private Array<Cloud> clouds = new Array<Cloud>();
    private final float DISTANCE_BETWEEN_CLOUDS = 250f;

    public CloudsController(World world){
        this.world = world;
        createClouds();
        positionClouds();
    }

    // create 2 dark clouds
    void createClouds(){
        for(int i = 0; i < 2; i++){
            clouds.add(new Cloud(world, "Dark Cloud"));
        }

        int index = 1;

        for(int i = 0; i < 6; i++){
            clouds.add(new Cloud(world, "Cloud " + index));
            index++;

            if(index == 4)
                index = 1;
        }

        clouds.shuffle();
    }

    public void positionClouds(){

        while(clouds.get(0).getCloudName() == "Dark Cloud"){
            clouds.shuffle();
        }

        float positionY = GameInfo.HEIGHT / 2f;
        float tempX = GameInfo.WIDTH /2f;

        for(Cloud c: clouds){
            c.setSpritePosition(tempX, positionY);
            positionY -= DISTANCE_BETWEEN_CLOUDS;
        }
    }

    public void drawClouds(SpriteBatch batch){
        for(Cloud c: clouds){
            batch.draw(c, c.getX() - c.getWidth() / 2f,
                    c.getY() - c.getHeight() / 2f);
        }
    }
}
