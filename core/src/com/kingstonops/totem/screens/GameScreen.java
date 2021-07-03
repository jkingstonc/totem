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
import com.kingstonops.totem.items.EmptyTotem;
import com.kingstonops.totem.items.InventoryComponent;
import com.kingstonops.totem.items.Item;
import com.kingstonops.totem.items.Pickaxe;
import com.kingstonops.totem.physics.ColliderComponent;
import com.kingstonops.totem.physics.MovementComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.player.PlayerComponent;
import com.kingstonops.totem.rendering.CameraComponent;
import com.kingstonops.totem.rendering.RenderComponent;
import com.kingstonops.totem.rendering.RenderSystem;
import com.kingstonops.totem.world.WorldSystem;
import com.kingstonops.totem.dialouge.DialougeComponent;
import com.kingstonops.totem.world.zones.ZoneComponent;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

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


        Item.registry.register("pickaxe", ()->new Pickaxe());
        Item.registry.register("speed_totem", ()->new EmptyTotem.SpeedTotem());



        m_game.engine().getSystem(WorldSystem.class).to_zone("starting_house_downstairs");



        m_player = m_game.engine().createEntity();
        m_game.engine().addEntity(m_player);


        ColliderComponent c = new ColliderComponent();
        c.m_solid = true;
        c.m_dynamic = true;
        c.m_bounds = new Vector2(RenderSystem.unit_to_pixel(.5f), RenderSystem.unit_to_pixel(.5f));
        m_player.add(c);


        InventoryComponent i = new InventoryComponent();
        i.put(Item.registry.instantiate("speed_totem"));
        i.put(Item.registry.instantiate("pickaxe"));
        m_player.add(i);

        TransformComponent t = new TransformComponent();
        t.position = new Vector3(
            RenderSystem.unit_to_pixel(0),
            RenderSystem.unit_to_pixel(0),
            RenderSystem.PLAYER_LAYER
        );
        m_player.add(t);
        MovementComponent v = new MovementComponent();
        m_player.add(v);
        m_player.add(new PlayerComponent());
        RenderComponent r = new RenderComponent();
        r.texture = new TextureRegion(new Texture("guy.png"));
        m_player.add(r);


        // add the item the player is holding


        Entity holding_item = m_game.engine().createEntity();
        TransformComponent i_t = new TransformComponent();
        i_t.position = new Vector3(
                RenderSystem.unit_to_pixel(0),
                RenderSystem.unit_to_pixel(0),
                RenderSystem.PLAYER_LAYER+1
        );
        i_t.scale = new Vector3(
                RenderSystem.unit_to_pixel(.5f),
                RenderSystem.unit_to_pixel(.5f),
                0
        );
        holding_item.add(i_t);
        RenderComponent i_r = new RenderComponent();
        i_r.texture=new TextureRegion(RenderSystem.get(i.m_items.get(0).items().get(0).m_texture));
        holding_item.add(i_r);
        m_game.engine().addEntity(holding_item);
        m_player.getComponent(PlayerComponent.class).m_holding_item = holding_item;




        // set the camera target

        CameraComponent cam = CameraComponent.getInstance(m_game.engine());
        System.out.println("camera = "+cam);
        cam.target = t.position;
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
