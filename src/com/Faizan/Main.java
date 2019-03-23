package com.Faizan;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.lang.model.element.Element;
import javax.xml.parsers.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;



public class Main {

    static File file;
    public static void main(String[] args) {


        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
         file = new File("/Users/Faizan/Desktop/DOMparser/src/com/Faizan/file.xml");
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document =  documentBuilder.parse(file);
             NodeList nodeList = document.getElementsByTagName("block");
             SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
             SAXParser saxParser = saxParserFactory.newSAXParser();

             saxParser.parse(file,new UserHandler());
             for (int i = 0; i <nodeList.getLength() ; i++) {
                func(i,nodeList.item(i));
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


   private static void func(int blocknumber,Node node) throws IOException, SAXException {

        if(node.getNodeName().equalsIgnoreCase("block")){
            NodeList BlockChildren = node.getChildNodes();
            try {
                System.out.println("Block Number " + blocknumber + "    " + "Attribute for the block " + "  " + node.getAttributes().getNamedItem("type") + "    " + "Block Parent =  " + node.getParentNode().getNodeName() + "     " + "Block Siblings" + node.getNextSibling().getNodeName() + "level    " +  blocknumber );

            }catch (NullPointerException e){}

            try {

                  if(node.getParentNode().getNodeName().equalsIgnoreCase("next")) {
                      if (node.getParentNode().getNodeName().equalsIgnoreCase("statement")) {
                          System.out.println("\n \n\n\n \n \n ++++++++++++++++++++++++++++++++-----------<New BLOCK STARTS HERE>----------------------++++++++++++++++++++++");
                          System.out.println("Block Number " + blocknumber + "    " + "Attribute for the block " + "  " + node.getAttributes().getNamedItem("type") + "    " + "Block Parent =  " + node.getParentNode().getNodeName() + "     " + "Block Siblings" + node.getNextSibling().getNodeName() + "level    " +  blocknumber);
                      }
                  }


           }catch (NullPointerException e){}


            for (int i = 0; i <BlockChildren.getLength() ; i++) {
                Node tempNode = BlockChildren.item(i);
                if(tempNode.getNodeName().equalsIgnoreCase("statement")){
                    System.out.println("------------------------------------------------------STATEMENT----------------------------------------------------------------------------- \n\n\n\n\n\n" + blocknumber);
                    System.out.println("statement tag at level " + blocknumber);
                    System.out.println("statement Tag parent: " + node.getParentNode().getNodeName() +"\n\n\n\n\n");
                }

                if(tempNode.getNodeName().equalsIgnoreCase("next")) {
                    Node tempChild = tempNode.getLastChild();
                    try {
                        System.out.println("++++++++++++++ " + " next tag at level " + blocknumber + "+++++++++++++++++++" + "!!!!!! NODE CHILD!!!!!! " + tempChild.getNodeName() + "         " + "++++++++Node Parent+++++++++" + tempNode.getParentNode().getNodeName() + "Comparison" + "  " + tempChild.compareDocumentPosition(tempNode));

                    }catch (NullPointerException e){}
                }

                if(tempNode.getNodeName().equalsIgnoreCase("value")){
                    System.out.println(tempNode.getNodeName() + "     " + "Value Attribute  : "+ tempNode.getAttributes().getNamedItem("name") +"        "+  "Parent Tag = " + tempNode.getParentNode().getNodeName());
                    NodeList valueChildren = tempNode.getChildNodes();
                    try {
                        System.out.println(valueChildren.item(i).getNodeName() + "     " + valueChildren.item(i).getAttributes().getNamedItem("type") + "     " + "Parent Tag =  " + tempNode.getParentNode().getNodeName());
                        if (valueChildren.item(i).getNodeName().equalsIgnoreCase("shadow")) {
                            NodeList shadowChildren = valueChildren.item(i).getChildNodes();
                            System.out.println(shadowChildren.item(i).getNodeName() + "    " + shadowChildren.item(i).getTextContent() + "     " + "Parent Tag :    " + node.getParentNode().getNodeName());
                            System.out.println();
                        }
                    }catch (NullPointerException e) {

                    }
                }
            }

        }
    }

static class UserHandler extends DefaultHandler{


        ArrayList<block> blockArrayList;
        ArrayList<statement> statementArraylist;

        static int depth;
        static int statementIndex;

        int statementStart,statementEnd,blockEnd,blockIndex,blockdepth;
        ArrayList<SuperStructure> arrayList;


    public UserHandler() {
       blockArrayList = new ArrayList<>();
       statementArraylist = new ArrayList<>();

       arrayList = new ArrayList<>();
    }




    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {

        SuperStructure superStructure = new SuperStructure(statementArraylist,blockArrayList);
        superStructure.function();




    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("Statement")){
            statementStart = depth;
            statementCreator();
            System.out.println("statement start found depth    "+ depth);
            System.out.println("Putting index on statement " + statementIndex);
            depth++;
            statementIndex++;




        }
        if(!qName.equalsIgnoreCase("statement")){
            depth++;
           // System.out.println("depth incremented due to some other tag "   + depth);
        }
        if(qName.equalsIgnoreCase("block")){
            blockdepth = depth;
            blockCreator();
            System.out.println("Block Depth  "  + (depth++) + "       "+"The block index is   " +blockIndex );
            blockIndex++;


        }



    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
     if(qName.equalsIgnoreCase("statement")){
         System.out.println("statement end found   " + "   "+depth   +"   " + "for the statement with index     "  + statementIndex--);
            statementEnd = depth;

         }
         if(qName.equalsIgnoreCase("block")){
             blockEnd = depth;
             blockIndex--;


         }

     }
    void statementCreator(){
        statement statement = new statement(statementStart,statementEnd,statementIndex);
        statementArraylist.add(statement);
    }
    void blockCreator(){
        block block = new block(blockdepth,blockIndex,blockEnd);
        blockArrayList.add(block);
    }

    }
}





