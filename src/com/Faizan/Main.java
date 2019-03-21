package com.Faizan;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {


        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        File file = new File("/Users/Faizan/Desktop/DOMparser/src/com/Faizan/file.xml");
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document =  documentBuilder.parse(file);
            NodeList nodeList = document.getElementsByTagName("block");
            for (int i = 0; i <nodeList.getLength() ; i++) {
                func(i+1,nodeList.item(i));
            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


   static void func(int blocknumber,Node node){

        if(node.getNodeName().equalsIgnoreCase("block")){
            NodeList BlockChildren = node.getChildNodes();
            System.out.println("Block Number " + blocknumber +"    "  +  "Attribute for the block "  + "  " + node.getAttributes().getNamedItem("type") +"    " + "Block Parent =  " + node.getParentNode().getNodeName());
            for (int i = 0; i <BlockChildren.getLength() ; i++) {
                Node tempNode = BlockChildren.item(i);
                if(tempNode.getNodeName().equalsIgnoreCase("statement")){
                    System.out.println("statement tag at level " + blocknumber);
                    System.out.println("Statement Tag parent: " + node.getParentNode().getNodeName());

                }

                if(tempNode.getNodeName().equalsIgnoreCase("next")){
                    System.out.println("next tag at level " + blocknumber);
                }

                if(tempNode.getNodeName().equalsIgnoreCase("value")){
                    System.out.println(tempNode.getNodeName() + "     " + "Value Attribute  : "+ tempNode.getAttributes().getNamedItem("name") +"        "+  "Parent Tag = " + tempNode.getParentNode().getNodeName());
                    NodeList valueChildren = tempNode.getChildNodes();
                    System.out.println(valueChildren.item(i).getNodeName() +"     "+ valueChildren.item(i).getAttributes().getNamedItem("type") + "     " + "Parent Tag =  " +tempNode.getParentNode().getNodeName()) ;
                    if(valueChildren.item(i).getNodeName().equalsIgnoreCase("shadow")) {
                        NodeList shadowChildren = valueChildren.item(i).getChildNodes();
                        System.out.println(shadowChildren.item(i).getNodeName()+"    " + shadowChildren.item(i).getTextContent() +"     " + "Parent Tag :    " +  node.getParentNode().getNodeName());
                        System.out.println();;



                    }
                }
            }

        }
    }

    static void BlockParser(Node node , int position){

    }
}
