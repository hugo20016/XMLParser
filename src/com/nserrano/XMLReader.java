package com.nserrano;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Objects;

public class XMLReader {

        public XMLReader(){

            File inputXML = new File("concesionario.xml"); // En un tipo FILE guardamos el documento xml normal

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); // Nos guardamos la "fabrica" de traductores
            DocumentBuilder dBuilder; // Creamos un traductor vacio, no sabe que va a traducir
            Document doc = null; // Aqui el documento sobre el que vamos a trabajar

            try {
                dBuilder = dbFactory.newDocumentBuilder(); // Inicializamos el traductor vacio con el metodo newDocumentBuilder()

                doc = dBuilder.parse(inputXML); // Mandamos a la variable "doc" el documento XML "traducido" (el xml listo para ser tratado)

            }
            catch (Exception e) {
                e.printStackTrace(); // La excepcion de si no existe inputXML o no esta bien escrito
            }
            Objects.requireNonNull(doc).getDocumentElement().normalize(); // Normalizamos el documento (ponemos todas las letras en el mismo formato, lo del UTF-8 este)

            NodeList nList = doc.getElementsByTagName("coche"); // Nos guardamos todos los tipos "coche" en la variable nList, que es una lista de nodos
            System.out.println("Numero de coches: " + nList.getLength());


            for(int temp = 0; temp < nList.getLength(); temp++) { // Un for de toda la vida de dios, pero el tio llama "temp" a "i"
                Node nNode = nList.item(temp); // Cogemos uno de los elementos (en este caso, coche), y lo guardamos como un nodo, que tiene
                                               // sus atributos normales

                if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode; // Transformamos el nodo a un elemento, para poder acceder a tooodos sus atributos

                    // Lo siguiente es muy HTML, los getElementsByTagName, getAttribute, y eso
                    System.out.println("\nCoche id: " + eElement.getAttribute("id"));
                    System.out.println("Marca: "
                            + eElement.getElementsByTagName("marca").item(0).getTextContent());
                    System.out.println("Modelo: "
                            + eElement.getElementsByTagName("modelo").item(0).getTextContent());
                    System.out.println("Cilindrada: "
                            + eElement.getElementsByTagName("cilindrada").item(0).getTextContent());
                }
            }


        }




}
