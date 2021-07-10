package com.kingstonops.totem.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.*;
import com.kingstonops.totem.items.*;
import com.kingstonops.totem.physics.ColliderComponent;
import com.kingstonops.totem.physics.MovementComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.player.Pet;
import com.kingstonops.totem.player.Player;
import com.kingstonops.totem.player.PlayerComponent;
import com.kingstonops.totem.rendering.CameraComponent;
import com.kingstonops.totem.rendering.RenderComponent;
import com.kingstonops.totem.rendering.RenderSystem;
import com.kingstonops.totem.world.WorldSystem;
import com.kingstonops.totem.dialouge.DialougeComponent;
import com.kingstonops.totem.world.animals.Animal;
import com.kingstonops.totem.world.animals.Chicken;
import com.kingstonops.totem.world.animals.Cow;
import com.kingstonops.totem.world.animals.Dog;
import com.kingstonops.totem.world.guys.AIComponent;
import com.kingstonops.totem.world.objects.Door;
import com.kingstonops.totem.world.tiles.Chair;
import com.kingstonops.totem.world.tiles.Tile;
import com.kingstonops.totem.world.tiles.Tree;
import com.kingstonops.totem.world.zones.Zone;
import com.kingstonops.totem.world.zones.ZoneComponent;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

import java.util.ArrayList;
import java.util.Arrays;

public class GameScreen extends ScreenAdapter {

    private Totem m_game;
    private Texture m_tex;
    private boolean m_initialised = false;

    private Entity m_player;

    public GameScreen(Totem game){
        m_game = game;
        m_tex = new Texture("badlogic.jpg");
    }

    private void init(){
        System.out.println("init");



        RenderSystem.register_all(m_game);
        ZoneComponent.register_all(m_game);
        DialougeComponent.register_all(m_game);


        Prefab.registry.register("player", (g)->new Player().spawn(g));
        Prefab.registry.register("animal_dog", (g)->new Dog().spawn(g));
        Prefab.registry.register("tile_tree_trunk", (g)->new Tile("tree_trunk", "tree_trunk.png", true).spawn(g));
        Prefab.registry.register("tile_tree_top", (g)->new Tile("tree_top", "tree_top.png", false).spawn(g));
        Prefab.registry.register("tile_barrier", (g)->new Tile("barrier", "barrier.png", true).spawn(g));
        Prefab.registry.register("tile_floor_board", (g)->new Tile("floor_board", "floor_board.png", false).spawn(g));
        Prefab.registry.register("tile_grass", (g)->new Tile("grass", "grass.png", false).spawn(g));
        Prefab.registry.register("tile_sand", (g)->new Tile("sand", "sand.png", false).spawn(g));
        Prefab.registry.register("tile_water", (g)->new Tile("water", "water.png", true).spawn(g));
        Prefab.registry.register("tile_path", (g)->new Tile("path", "path.png", false).spawn(g));
        Prefab.registry.register("obj_fence", (g)->new Tile("fence", "fence.png", true).spawn(g));
        Prefab.registry.register("obj_tree", (g)->new Tree().spawn(g));
        Prefab.registry.register("obj_hay", (g)->new Tile("hay", "hay.png", true).spawn(g));
        Prefab.registry.register("obj_door", (g)->new Door().spawn(g));
        Prefab.registry.register("animal_cow", (g)->new Cow().spawn(g));


        Item.registry.register("lead", (g)->new LeadItem());
        Item.registry.register("pickaxe", (g)->new Pickaxe());
        Item.registry.register("speed_totem", (g)->new EmptyTotem.SpeedTotem());
        Item.registry.register("rusty_gear", (g)->new Misc.RustyGear());
        Item.registry.register("grass", (g)->new Item("grass", "grass.png", Item.Rarity.COMMON));
        Item.registry.register("chair", (g)->new Misc.Chair());
        Item.registry.register("spawn_fence", (g)->new Misc.SpawnFence());
        Item.registry.register("spawn_cow", (g)->new Misc.SpawnCow());
        Item.registry.register("spawn_chicken", (g)->new Misc.SpawnChicken());
        Item.registry.register("spawn_hay", (g)->new Misc.SpawnHay());
        Item.registry.register("spawn_tree", (g)->new Misc.SpawnTree());





        //Recipe.registry.register("speed_totem_recipe", ()->new Recipe("speed_totem_recipe", new ArrayList<>(Arrays.asList("base_totem", "speed_berry")), null));*/



        Entity player = Prefab.registry.instantiate("player").spawn(m_game);
        //Zone.from_file(m_game, "zones/test.tmx");
        //m_game.engine().getSystem(WorldSystem.class).to_zone("starting_house_downstairs");
        m_game.engine().getSystem(WorldSystem.class).to_zone("zones/zone_town_0_house_0.tmx");



        Entity dog = Prefab.registry.instantiate("animal_dog").spawn(m_game);
        dog.getComponent(TransformComponent.class).position.set(5,5,1);
        dog.getComponent(AIComponent.class).m_ai_provier = new Pet.PetAIProvider();


        player.getComponent(PlayerComponent.class).m_pets.add(dog);



        // set the camera target

        CameraComponent cam = CameraComponent.getInstance(m_game.engine());
        System.out.println("camera = "+cam);
        cam.target = player.getComponent(TransformComponent.class).position;
        cam.follow_target=true;







        m_imgui_glfw = new ImGuiImplGlfw();
        m_imgui_gl3 = new ImGuiImplGl3();
        GLFWErrorCallback.createPrint(System.err).set();
        if(!GLFW.glfwInit()){
            System.out.println("failed to init GLFW!");
        }
        ImGui.createContext();
        final ImGuiIO io = ImGui.getIO();
        io.setIniFilename(null);

        m_win_handle = ((Lwjgl3Graphics) Gdx.graphics).getWindow().getWindowHandle();

        m_imgui_glfw.init(m_win_handle, true);
        m_imgui_gl3.init();

        m_initialised = true;

        m_profiler = new GLProfiler(Gdx.graphics);
        m_profiler.enable();
    }
    private GLProfiler m_profiler;
    private ImGuiImplGlfw m_imgui_glfw;
    private ImGuiImplGl3 m_imgui_gl3;
    private long m_win_handle;


    @Override
    public void resize(int w, int h){
        CameraComponent.getInstance(m_game.engine()).resize(w, h);
    }

    private void update(float dt){

//        Gdx.gl.glClearColor(0,255,0,100);
//        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT | Gdx.gl20.GL_DEPTH_BUFFER_BIT);

        m_imgui_glfw.newFrame();
        ImGui.newFrame();

        m_game.engine().update(dt);

        if(Debug.DEBUG) {
            ImGui.begin("debug info");
            ImGui.text("version " + Totem.VERSION);
            ImGui.text("fps " + (1 / dt));
            ImGui.text("entities " + m_game.engine().getEntities().size());
            ImGui.text("draw calls " + m_profiler.getDrawCalls());
            ImGui.text("vertex count " + m_profiler.getVertexCount());
            ImGui.text("shader switches " + m_profiler.getShaderSwitches());
            ImGui.end();
        }

        ImGui.endFrame();
        ImGui.render();
        m_imgui_gl3.renderDrawData(ImGui.getDrawData());
    }

    @Override
    public void show(){

    }

    @Override
    public void dispose(){
        m_tex.dispose();
    }

    @Override
    public void render(float dt){
        if(m_initialised){
            update(dt);
        }else{
            init();
        }
    }


}
