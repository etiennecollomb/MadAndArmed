package com.geekmecrazy.madandarmed.SaveState;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.geekmecrazy.madandarmed.Utils.Vector2d;

public class Fight_SaveGameState {

	public Fight_SaveGameState(){

        Vector2d someObject=new Vector2d();
        someObject.set(1,2);

        //serialise object

        //try-with-resources used to autoclose resources
        try (Output output = new Output(new FileOutputStream("KryoTest.ser"))) {
            Kryo kryo=new Kryo();
            kryo.writeClassAndObject(output, someObject);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Fight_SaveGameState.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //deserialise object

        Vector2d retrievedObject=null;

        try (Input input = new Input( new FileInputStream("KryoTest.ser"))){
            Kryo kryo=new Kryo();
            retrievedObject=(Vector2d)kryo.readClassAndObject(input);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Fight_SaveGameState.class.getName()).log(Level.SEVERE, null, ex);
        }


        System.out.println("Retrieved from file: " + retrievedObject.toString() + " " + retrievedObject.getY());
    }

}
