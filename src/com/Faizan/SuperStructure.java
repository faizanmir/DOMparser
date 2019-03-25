package com.Faizan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class SuperStructure {
    ArrayList<statement> statements;
    ArrayList<block> blocks;
    static HashMap<Integer,Integer> BlockData;
    ArrayList<Integer>notInAnyBlock;
    ArrayList<Integer> i;
   ArrayList<String> AppliedBlockAttributes;
   HashMap<Integer,String >AttributeFinal;
    block b;



    public SuperStructure(ArrayList<statement> statements, ArrayList<block> blocks) {
        this.statements = statements;
        this.blocks = blocks;
        BlockData = new HashMap<>();
        AppliedBlockAttributes = new ArrayList<>();




    }

   public void function() {


       for(statement e :statements){
           for (block b:blocks)
           {
               if(b.block_depth==1){
               AppliedBlockAttributes.add(b.attributes);
                }

               if(e.start<=b.block_depth){


                   System.out.println("Block     "+ b.blockIndex  + "     is under    "    + e.index +  "   Attributes =  " + b.attributes  );

                   BlockData.put(b.blockIndex,e.index);


                   if(e.index==0){
                       AppliedBlockAttributes.add(b.attributes);
                   }


                   if(e.index==0)
                   System.out.println("Control String : " + AppliedBlockAttributes);
               }

           }


       }
        System.out.println("\n\n\n\n\n Final block hierarchy levels \n\n\n\n\n"  + BlockData.entrySet());
        System.out.println("----------------------------------------SAXPARSER END----------------------------------------------------------------- ");
        }

    }

