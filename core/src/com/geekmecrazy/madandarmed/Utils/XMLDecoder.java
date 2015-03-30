package com.geekmecrazy.madandarmed.Utils;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.badlogic.gdx.files.FileHandle;


/**
 * @author Bonett
 *
 * Class Brieff: Decodes the xml files through any partiuclar handler
 * Singleton
 */
public class XMLDecoder {
        
        private static XMLDecoder _instance;
        
        private SAXParserFactory         _saxFactory;
        private SAXParser                       _parser;
        private DefaultHandler          _handler;
        
        public static XMLDecoder getInstance()
        {
                if(_instance == null)
                {
                        _instance = new XMLDecoder();
                }
                
                return _instance;
        }
        
        private XMLDecoder()
        {
                try
                {
                        _saxFactory = SAXParserFactory.newInstance();
                        _parser = _saxFactory.newSAXParser();
                }
                catch(ParserConfigurationException e)
                {
                System.err.println("error de  parseo");
                }
                catch(SAXException e2)
                {
                System.err.println("error de  sax : " + e2.getStackTrace());
        } 
        }
        
        public void parseXML(FileHandle filehandle, DefaultHandler handler)
        {                
                _handler = handler;
                
                try
                {
                        _parser.parse(filehandle.read(), _handler);
                }
                catch (IOException e3) 
                {
                        // TODO Auto-generated catch block
                System.err.println("error de  io : " + e3.getMessage() );
        }
                catch(SAXException e2)
                {
                System.err.println("error de  sax : " + e2.getStackTrace());
        } 
        }
}