package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import guis.GuiRenderer;
import guis.GuiTexture;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;

public class MainGameLoop {
	 
	public static void main(String[] args) {
		 
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        
        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy2"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("mud"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("grassFlowers"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("footpath"));

        
        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture,rTexture,gTexture,bTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));
        
         
       
         
        TexturedModel staticModel = new TexturedModel(OBJLoader.loadObjModel("fern", loader),new ModelTexture(loader.loadTexture("fern")));
        staticModel.getTexture().setHasTransparency(true);
        staticModel.getTexture().setUseFakeLighting(true);
        TexturedModel lowPolyTree = new TexturedModel(OBJLoader.loadObjModel("lowPolyTree", loader),new ModelTexture(loader.loadTexture("lowPolyTree")));
        TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader),new ModelTexture(loader.loadTexture("grassTexture")));
        grass.getTexture().setHasTransparency(true);
        grass.getTexture().setUseFakeLighting(true);
        TexturedModel woodenTower = new TexturedModel(OBJLoader.loadObjModel("woodenTower", loader),new ModelTexture(loader.loadTexture("woodTower")));
        TexturedModel flower = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader),new ModelTexture(loader.loadTexture("flower")));
        flower.getTexture().setHasTransparency(true);
        flower .getTexture().setUseFakeLighting(true);
        TexturedModel farmhouse = new TexturedModel(OBJLoader.loadObjModel("dragon", loader),new ModelTexture(loader.loadTexture("stallTexture")));
        List<Terrain> terrains = new ArrayList<Terrain>();
        
        Terrain terrain0 = new Terrain(0,0,loader,texturePack,blendMap,"heightmap");
        terrains.add(terrain0);
        Terrain terrain1 = new Terrain(0,1,loader,texturePack,blendMap,"heightmap");
        terrains.add(terrain1);
        Terrain terrain2 = new Terrain(1,1,loader,texturePack,blendMap,"heightmap");
        terrains.add(terrain2);
        Terrain terrain3 = new Terrain(1,0,loader,texturePack,blendMap,"heightmap");
        terrains.add(terrain3);
        Terrain terrain4 = new Terrain(0,-1,loader,texturePack,blendMap,"heightmap");
        terrains.add(terrain4);
        Terrain terrain5 = new Terrain(-1,-1,loader,texturePack,blendMap,"heightmap");
        terrains.add(terrain5);
        Terrain terrain6 = new Terrain(-1,1,loader,texturePack,blendMap,"heightmap");
        terrains.add(terrain6);
        Terrain terrain7 = new Terrain(1,-1,loader,texturePack,blendMap,"heightmap");
        terrains.add(terrain7);
        Terrain terrain8 = new Terrain(-1,0,loader,texturePack,blendMap,"heightmap");
        terrains.add(terrain8);

        List<Entity> entities = new ArrayList<Entity>();
        Random random = new Random();
        for(Terrain terrain:terrains) {
        for(int i=0;i<30;i++){
        	float x = random.nextInt((int) terrain.getSize()) + terrain.getX()-400;
			float z = random.nextInt((int) terrain.getSize()) + terrain.getZ();
			float y = terrain.getHeightOfTerrain(x, z);
            entities.add(new Entity(staticModel, new Vector3f(x,y,z),0,random.nextFloat() *360,0,0.9f));
        }
        for(int i=0;i<8;i++){
        	float x = random.nextInt((int) terrain.getSize()) + terrain.getX()-400;
			float z = random.nextInt((int) terrain.getSize()) + terrain.getZ();
			float y = terrain.getHeightOfTerrain(x, z);
            entities.add(new Entity(woodenTower, new Vector3f(x,y,z),0,0,0,3));
        }
        
        for(int i=0;i<3;i++){
        	float x = random.nextInt((int) terrain.getSize()) + terrain.getX()-400;
			float z = random.nextInt((int) terrain.getSize()) + terrain.getZ();
			float y = terrain.getHeightOfTerrain(x, z);
            entities.add(new Entity(farmhouse, new Vector3f(x,y,z),0,0,0,3));
        }
        
        for(int i=0;i<75;i++){
        	float x = random.nextInt((int) terrain.getSize()) + terrain.getX()-400;
			float z = random.nextInt((int) terrain.getSize()) + terrain.getZ();
			float y = terrain.getHeightOfTerrain(x, z);
            entities.add(new Entity(lowPolyTree, new Vector3f(x,y,z),0,0,0,3));
        }
        for(int i=0;i<50;i++){
        	float x = random.nextInt((int) terrain.getSize()) + terrain.getX()-400;
			float z = random.nextInt((int) terrain.getSize()) + terrain.getZ();
			float y = terrain.getHeightOfTerrain(x, z);
            entities.add(new Entity(grass, new Vector3f(x,y,z),0,0,0,3));
        }
        for(int i=0;i<50;i++){
        	float x = random.nextInt((int) terrain.getSize()) + terrain.getX()-400;
			float z = random.nextInt((int) terrain.getSize()) + terrain.getZ();
			float y = terrain.getHeightOfTerrain(x, z);
            entities.add(new Entity(flower, new Vector3f(x,y,z),0,0,0,3));
        }
        }
         
        Light light = new Light(new Vector3f(20000,20000,2000),new Vector3f(1,1,1));
        
        MasterRenderer renderer = new MasterRenderer(loader);
        
        RawModel stickman = OBJLoader.loadObjModel("stickman", loader);
        TexturedModel stickmanModel = new TexturedModel(stickman, new ModelTexture(loader.loadTexture("playerTexture")));
        
        Player player = new Player(stickmanModel,new Vector3f(300,5,450),0,0,0,1.5f);
        Camera camera = new Camera(player);	
        
        List<GuiTexture>guis = new ArrayList<GuiTexture>();
        //GuiTexture gui =new GuiTexture(loader.loadTexture("brook"),new Vector2f(0.5f,0.5f),new Vector2f(0.25f,0.25f));
        //	guis.add(gui);
        
        GuiRenderer guiRenderer = new GuiRenderer(loader);
        while(!Display.isCloseRequested()){
            camera.move();
            for(Terrain terrain : terrains) {
                if(terrain.getX() <= player.getPosition().x) { 
                 if(terrain.getX() + terrain.getSize() > player.getPosition().x) {
                  if(terrain.getZ() <= player.getPosition().z) {
                   if(terrain.getZ() + terrain.getSize() > player.getPosition().z) {
                    player.move(terrain);
                    renderer.processEntity(player);
                   }
                  }
                 }
                }
               }

            for(Terrain terrain : terrains) {
    			renderer.processTerrain(terrain);
    			}
            for(Entity entity:entities){
                renderer.processEntity(entity);
            }
            renderer.render(light, camera);
            guiRenderer.render(guis);
            DisplayManager.updateDisplay();
        }
        
        terrains.clear();
        guiRenderer.cleanUp();
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
 
    }	
 
}