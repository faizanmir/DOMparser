package com.Faizan;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.lang.model.element.Element;
import javax.xml.parsers.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Main {

    static File file;
    public static void main(String[] args) {


        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
         file = new File("/Users/Faizan/Desktop/DOMparser/src/com/Faizan/file.xml");
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document =  documentBuilder.parse(file);





             NodeList nodeList = document.getElementsByTagName("block");

             for (int i = 0; i <nodeList.getLength() ; i++) {
                func(i+1,nodeList.item(i));
            }

            NodeList statementNodeList = document.getElementsByTagName("statement");

            for (int i = 0; i <statementNodeList.getLength() ; i++) {
                statementHeirarchyFinder(i,statementNodeList.item(i));
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
                System.out.println("Block Number " + blocknumber + "    " + "Attribute for the block " + "  " + node.getAttributes().getNamedItem("type") + "    " + "Block Parent =  " + node.getParentNode().getNodeName() + "     " + "Block Siblings" + node.getNextSibling().getNodeName() + "level    " +  blocknumber);

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
                    System.out.println("statement tag at level " + blocknumber);
                    System.out.println("Statement Tag parent: " + node.getParentNode().getNodeName());
                    System.out.println("------------------------------------------------------STATEMENT----------------------------------------------------------------------------- " + blocknumber);


                }

                if(tempNode.getNodeName().equalsIgnoreCase("next")) {
                    Node tempChild = tempNode.getLastChild();
                    try {
                        System.out.println("++++++++++++++ " + "next tag at level " + blocknumber + "+++++++++++++++++++" + "!!!!!! NODE CHILD!!!!!! " + tempChild.getNodeName() + "         " + "++++++++Node Parent+++++++++" + tempNode.getParentNode().getNodeName() + "Comparison" + "  " + tempChild.compareDocumentPosition(tempNode));

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
    public static void statementHeirarchyFinder(int statementNumber,Node node){
        NodeList blocksInStatement = node.getChildNodes();
       // System.out.println("HEIRARCHY EXECUTING");
        int numberOfBlocksinStatement = 0 ;
        try {
            for (int i = 0; i <blocksInStatement.getLength() ; i++) {
                System.out.println(blocksInStatement.item(i).getNodeName());
                if(blocksInStatement.item(i).getNodeName().equalsIgnoreCase("block"))
                {       numberOfBlocksinStatement++;
                    NodeList blockChildNodeList = blocksInStatement.item(i).getChildNodes();
                    if(blockChildNodeList.item(i).getNodeName().equalsIgnoreCase("next")){
                        numberOfBlocksinStatement++;
                    }
                }
                    System.out.println("Block is in statement " + statementNumber +  "     "  + numberOfBlocksinStatement);
            }
        }catch (NullPointerException e){}



    }


}

