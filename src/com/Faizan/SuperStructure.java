package com.Faizan;

import java.util.ArrayList;

public class SuperStructure {
    ArrayList<statement> statements;
    ArrayList<block> blocks;

    public SuperStructure(ArrayList<statement> statements, ArrayList<block> blocks) {
        this.statements = statements;
        this.blocks = blocks;
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
               }
           }
       }

        }

    }

