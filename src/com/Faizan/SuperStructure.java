package com.Faizan;

import java.util.ArrayList;
import java.util.HashMap;



public class SuperStructure {
    ArrayList<statement> statements;
    ArrayList<block> blocks;
    static HashMap<Integer,Integer> BlockData;
    block blockObject;
    statement staementObject;

    public SuperStructure(ArrayList<statement> statements, ArrayList<block> blocks) {
        this.statements = statements;
        this.blocks = blocks;
        BlockData = new HashMap<>();


    }

    public void function() {
        for (int i = 0; i < statements.size(); i++) {
            System.out.println("Statement start at " + statements.get(i).start);
        }
        for (int i = 0; i < blocks.size(); i++) {
            System.out.println("Block starts at " + blocks.get(i).block_depth);

        }
       for(statement e :statements){
           for (block b :blocks)
           {
               if(e.start<b.block_depth){
                   System.out.println("Block     "+ b.blockIndex  + "     is under    "    + e.index);
                   BlockData.put(b.blockIndex,e.index);

               }
           }
       }
        System.out.println("\n\n\n\n\n Final block hierarchy level \n\n\n\n\n"  + BlockData.entrySet());




        System.out.println("----------------------------------------SAXPARSER END----------------------------------------------------------------- ");
        }

    }

