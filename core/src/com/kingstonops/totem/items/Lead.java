package com.kingstonops.totem.items;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Debug;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.Utils;
import com.kingstonops.totem.player.Pet;
import com.kingstonops.totem.world.animals.AnimalComponent;
import com.kingstonops.totem.world.guys.AIComponent;


public class Lead extends Item{


    private Entity being_lead = null;

    public Lead(){
        super("lead_item", "lead.png", Rarity.COMMON);
    }

    @Override
    public void on_use(Totem game, Vector3 pos) {

        Debug.dbg("using lead!");


        Entity e = Utils.raycast_entity(game, pos);
        if(e!=null){
            if(e.getComponent(AnimalComponent.class)!=null){

                if(being_lead!=e){
                    being_lead = e;
                    e.getComponent(AIComponent.class).m_ai_provier = new Pet.PetAIProvider();
                }else{
                    being_lead=null;
                    e.getComponent(AIComponent.class).m_ai_provier = new AIComponent.AIProvider.BasicWanderingAIProvider();
                }



                Debug.dbg("found animal!");


            }
        }

    }
}
